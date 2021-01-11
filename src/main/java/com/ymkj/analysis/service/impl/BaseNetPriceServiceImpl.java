package com.ymkj.analysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.*;
import com.ymkj.analysis.repository.BaseNetPriceMapper;
import com.ymkj.analysis.service.BaseAreaMapper;
import com.ymkj.analysis.service.BaseNetPriceService;
import com.ymkj.analysis.entity.query.NetPriceQuery;
import com.ymkj.analysis.entity.query.PikeNetPriceQuery;
import com.ymkj.analysis.entity.dto.NetPriceSourceDTO;
import com.ymkj.analysis.entity.dto.NetPriceDTO;
import com.ymkj.analysis.entity.dto.PikeNetPriceDTO;
import com.ymkj.analysis.utils.BeanMapper;
import com.ymkj.analysis.utils.DateUtil;
import com.ymkj.analysis.utils.SystemConst;
import com.ymkj.analysis.entity.enums.NetPriceSource;
import com.ymkj.analysis.entity.enums.SteelCityCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 网价业务实现
 *
 * @author tao
 */
@Slf4j
@Service
public class BaseNetPriceServiceImpl implements BaseNetPriceService {
    private static final String GAOXIAN = "高线";
    private static final String PANYUAN = "盘圆";
    private static final List<String> GAOXIAO_PANYUAN = Arrays.asList("高线", "盘圆");
    private static final Integer TWO = 2;
    private static final String FI = "Ф";
    private static final String SPLIT_LINE = "-";


    @Autowired
    private BaseNetPriceMapper netPriceMapper;
    @Autowired
    private SteelConfigEntity steelConfigEntity;
    @Autowired
    private BaseAreaMapper areaMapper;


    @Override
    public Page<BaseNetPrice> getPageBySameTime(NetPriceQuery query) {
        handleMaterial(query);
        return netPriceMapper.selectPage(new Page<>(query.getPage(), query.getPageSize()),
                new LambdaQueryWrapper<BaseNetPrice>()
                        .eq(StringUtils.hasText(query.getMaterialName()), BaseNetPrice::getMaterialName, query.getMaterialName())
                        .eq(StringUtils.hasText(query.getMaterialMaterial()), BaseNetPrice::getMaterial, query.getMaterialMaterial())
                        .eq(StringUtils.hasText(query.getMaterialSpec()), BaseNetPrice::getSpecification, query.getMaterialSpec())
                        .between(null!=query.getPublishDateStart()&&null!=query.getPublishDateEnd(), BaseNetPrice::getPublishTime, query.getPublishDateStart(), query.getPublishDateEnd())
                        .in(CollectionUtils.isNotEmpty(query.getAreas()), BaseNetPrice::getArea, query.getAreas())
                        .orderByDesc(BaseNetPrice::getPublishTime)
                        .orderByAsc(BaseNetPrice::getPrice)
        );
    }

    @Override
    public Page<BaseNetPrice> getPageBySameArea(NetPriceQuery query) {
        handleMaterial(query);
        return null;
    }

    @Override
    public Page<BaseNetPrice> getPageBySameManufacturer(NetPriceQuery query) {
        handleMaterial(query);
        return null;
    }

    @Override
    public Page<BaseNetPrice> getPage(NetPriceQuery query) {
        Set<String> materialNames = handleMaterialName(query);
        List<String> areas = handleAreas(query.getArea());
        return netPriceMapper.selectPage(new Page<>(query.getPage(), query.getPageSize()),
            new LambdaQueryWrapper<BaseNetPrice>()
                .in(materialNames != null && !materialNames.isEmpty(), BaseNetPrice::getMaterialName, materialNames)
                .between(query.getPublishDate() != null, BaseNetPrice::getPublishTime, query.getPublishDate() + " 00:00:00", query.getPublishDate() + " 23:59:59")
                .in(StringUtils.hasText(query.getArea()), BaseNetPrice::getArea, areas)
                .orderByDesc(BaseNetPrice::getPublishTime)
                .orderByDesc(BaseNetPrice::getPrice)
        );
    }
    /**
     * 处理物料 物料名称-材质-规格
     * @param query
     **/
    private void handleMaterial(NetPriceQuery query){
        String material = query.getMaterial();
        String[] split = material.split("-");
        String s = split[0];
        String materialName = "";
        if (StringUtils.hasText(s)) {
            if (s.equals(PANYUAN)) {
                materialName=GAOXIAN;
            }else if (s.equals(GAOXIAN)){
                materialName=PANYUAN;
            }
        }
        query.setMaterialName(materialName);
        query.setMaterialMaterial(split[1]);
        query.setMaterialSpec(split[2]);
    }
    private Set<String> handleMaterialName(NetPriceQuery query){
        Set<String> materialNames = null;
        if (query.getMaterialNames() != null && !query.getMaterialNames().isEmpty()) {
            materialNames = new HashSet<>(query.getMaterialNames());
            if (materialNames.contains(PANYUAN)) {
                materialNames.add(GAOXIAN);
            }
            if (materialNames.contains(GAOXIAN)) {
                materialNames.add(PANYUAN);
            }
        }
        return materialNames;
    }
    /**
     * 匹配网价区域
     *
     * @param areaId 查询对象
     * @return 区域
     */
    private List<String> handleAreas(String areaId) {
        List<BaseArea> areas = new ArrayList<>();
        if (StringUtils.hasText(areaId)) {
            // 若ID 为中国，则需要获取省和市
            if (SystemConst.CHINA_ID.equals(areaId)) {
                List<BaseArea> provinces = areaMapper.selectList(new LambdaQueryWrapper<BaseArea>().eq(BaseArea::getParentId, areaId));
                areas.addAll(provinces);
                provinces.stream().forEach(area -> {
                    List<BaseArea> cities = areaMapper.selectList(new LambdaQueryWrapper<BaseArea>().eq(BaseArea::getParentId, area.getId()));
                    if (!cities.isEmpty()) {
                        areas.addAll(cities);
                    }
                });
            } else {
                BaseArea baseArea = areaMapper.selectById(areaId);
                //若为省，则需要获取市，否则不获取
                if (baseArea.getAreaType() < TWO) {
                    List<BaseArea> cities = areaMapper.selectList(new LambdaQueryWrapper<BaseArea>().eq(BaseArea::getParentId, baseArea.getId()));
                    if (!cities.isEmpty()) {
                        areas.addAll(cities);
                    }
                }
                areas.add(baseArea);
            }
        }
        return areas.stream().map(BaseArea::getName).collect(Collectors.toList());
    }

    /**
     * 获取所有字节点
     *
     * @param areaId 副节点ID
     * @param areas  节点列表
     */
    private void getAllChildAreas(String areaId, List<BaseArea> areas) {
        List<BaseArea> childAreas = areaMapper.selectList(new LambdaQueryWrapper<BaseArea>().eq(BaseArea::getParentId, areaId));
        if (!childAreas.isEmpty()) {
            areas.addAll(childAreas);
            childAreas.stream().forEach(area -> getAllChildAreas(area.getId(), areas));
        }
    }

    /**
     * 解析网价数据
     *
     * @param steelDetailMap 参数
     */
    public List<BaseNetPrice> parseNetPriceDetail(Map<Integer, List<SteelDetail>> steelDetailMap) {
        Set<Integer> cityCodes = steelDetailMap.keySet();
        return cityCodes.parallelStream().flatMap(code -> {
            List<SteelDetail> steelDetails = steelDetailMap.get(code);
            String cityName = SteelCityCode.get(code);
            return steelDetails.parallelStream().flatMap(steelDetail -> {
                Date publishTime = DateUtil.parseDate(steelDetail.getPublishTime(), "yyyy-MM-dd HH:mm:ss");
                String title = steelDetail.getTitle();
                List<NetPrice> netPrices = steelDetail.getContents();
                return netPrices.parallelStream().map(netPrice -> {
                    return getBaseSteel(code, cityName, publishTime, title, netPrice);
                });
            });
        }).collect(Collectors.toList());
    }

    /**
     * 转换数据类型
     *
     * @param code        城市编码
     * @param cityName    省市名称
     * @param publishTime 发布时间
     * @param title       标题
     * @param netPrice    网价信息
     * @return result
     */
    private BaseNetPrice getBaseSteel(Integer code, String cityName, Date publishTime, String title, NetPrice netPrice) {
        BaseNetPrice baseSteel = new BaseNetPrice();
        baseSteel.setSerialNum(0L);
        baseSteel.setManufacturer(netPrice.getPlace());
        baseSteel.setCategory(netPrice.getCategory());
        baseSteel.setMaterialName(netPrice.getBreed());
        baseSteel.setMaterial(netPrice.getMaterial());
        baseSteel.setNote(netPrice.getNote());
        baseSteel.setArea(cityName);
        baseSteel.setPrice(netPrice.getPrice());
        baseSteel.setRaise(netPrice.getRaise());
        baseSteel.setSpecification(netPrice.getSpec());
        baseSteel.setUnit(netPrice.getUnit());
        baseSteel.setRowId(netPrice.getRowId());
        baseSteel.setPublishTime(publishTime);
        baseSteel.setSource(NetPriceSource.MY_STEEL.toInteger());
        baseSteel.setDelete((byte) 0);
        baseSteel.setTitle(title);
        baseSteel.setTableId(code.toString());
        baseSteel.setCreateTime(new Date());
        baseSteel.setUpdateTime(new Date());
        return baseSteel;
    }

    /**
     * 保存网价信息
     *
     * @param collect 网价变更明细
     */
    private void saveNetPriceDetail(List<BaseNetPrice> collect) {
        collect.stream().forEach(baseSteel -> {
            List<BaseNetPrice> baseNetPrices = netPriceMapper.selectNetPriceByCon(baseSteel);
            if (baseNetPrices == null || baseNetPrices.isEmpty()) {
                netPriceMapper.insert(baseSteel);
            }
        });
    }
    @Override
    public void getNetPriceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        List<Integer> tableList = steelConfigEntity.getTableIds();

        Map<Integer, List<SteelDetail>> steelDetailMap = new HashMap<>(5);
        tableList.forEach(id -> {
//            List<SteelDetail> steelDetails = getCityNetPriceFromSteelNet(date, id);
//            if (steelDetails != null && !steelDetails.isEmpty()) {
//                steelDetailMap.put(id, steelDetails);
//            }
//            try {
//                Thread.sleep(2000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
        saveNetPriceDetail(parseNetPriceDetail(steelDetailMap));
    }
//
//    /**
//     * 查询指定城市指定日期的网价数据
//     *
//     * @param date   日期
//     * @param cityId 城市ID
//     * @return result
//     */
//    private List<SteelDetail> getCityNetPriceFromSteelNet(String date, Integer cityId) {
//        log.info(".............................. getCityNetPriceFromSteelNet ...... date : {} ;cityId : {} ...............................", date, cityId);
//
//        String gatherUrl = steelConfigEntity.getGatherUrl();
//        String token = steelConfigEntity.getToken();
//
//        Map<String, String> param = new HashMap(5);
//        param.put("page", "1");
//        param.put("tableId", "" + cityId + "");
//        param.put("pageSize", "100");
//        param.put("token", token);
//        param.put("startTime", date);
//        param.put("endTime", date);
//        //获取发布变更数据
//        Response response = CommonApiClient.generateGetRequestAndGetResponse(gatherUrl.concat("?").concat(HttpHelper.params(param, true)));
//        //判断返回状态值
//        if (String.valueOf(response.code()).startsWith(String.valueOf(HttpStatus.Series.SUCCESSFUL.value()))) {
//            log.info("........................................... success to return response ........................................... ");
//            //请求成功
//            SteelChangeRow steelChangeRow = ResponseParseUtil.parseResponse2Obj(response, SteelChangeRow.class);
//            if (steelChangeRow != null && !steelChangeRow.getMarkets().isEmpty()) {
//                return doDetail(steelChangeRow);
//            }
//        }
//        log.info("........................................... fail to return response ........................................... ");
//        return Collections.emptyList();
//    }

    @Override
    public List<String> getMaterialNameList() {
        return netPriceMapper.selectMaterialNameList();
    }

    @Override
    public List<NetPriceSourceDTO> getSource() {
        NetPriceSourceDTO sourceVO = new NetPriceSourceDTO(1, "我的钢铁网");
        List<NetPriceSourceDTO> result = new ArrayList<>();
        result.add(sourceVO);
        return result;
    }

    @Override
    public List<PikeNetPriceDTO> selectPikeNetPrice(List<PikeNetPriceQuery> netPriceQuery) {
        List<PikeNetPriceDTO> netPrices = new ArrayList<>();
        netPriceQuery.stream().forEach(query -> {
            Integer count = netPriceMapper.selectNetPriceCount(
                StringUtils.replace(query.getArea(), " ", ""),
                query.getPublishTime() + " 00:00:00",
                query.getPublishTime() + " 23:59:59");
            // 判断库中是否有该城市指定日期网价数据,有则从数据库获取,没有则从钢铁网重新获取
            PikeNetPriceDTO pikeNetPrice = BeanMapper.map(query, PikeNetPriceDTO.class);
            if (count != null && count > 0) {
                pikeNetPrice.setNetPrices(getNetPriceFromDb(query));
            }
            /*else {
                Integer cityId = SteelCityCode.getCityId(query.getArea());
                if (cityId != null) {
                    List<SteelDetail> cityNetPrice = getCityNetPriceFromSteelNet(query.getPublishTime(), cityId);
                    if (cityNetPrice != null && !cityNetPrice.isEmpty()) {
                        HashMap<Integer, List<SteelDetail>> map = new HashMap<>(1);
                        map.put(cityId, cityNetPrice);
                        saveNetPriceDetail(parseNetPriceDetail(map));
                        pikeNetPrice.setNetPrices(getNetPriceFromDb(query));
                    }
                }
            }*/
            netPrices.add(pikeNetPrice);
        });

        return netPrices;
    }

    /**
     * 查询网价
     *
     * @param query 查询条件
     * @return result
     */
    private List<NetPriceDTO> getNetPriceFromDb(PikeNetPriceQuery query) {
        String area = StringUtils.replace(query.getArea(), " ", "");
        String material = StringUtils.replace(query.getMaterial(), " ", "");
        List<String> materialNames = null;
        String materialName = StringUtils.replace(query.getMaterialName(), " ", "");
        if (GAOXIAO_PANYUAN.contains(materialName)) {
            materialNames = GAOXIAO_PANYUAN;
        } else {
            materialNames = Collections.singletonList(materialName);
        }
        List<String> manufacturerList = query.getManufacturer().stream().map(manufacturer -> StringUtils.replace(manufacturer, " ", "")).collect(Collectors.toList());
        String publishTime = StringUtils.replace(query.getPublishTime(), " ", "");
        List<BaseNetPrice> netPrices = netPriceMapper.selectNetPriceList(
            area, material,
            materialNames,
            manufacturerList,
            publishTime + " 00:00:00",
            publishTime + " 23:59:59");
        List<BaseNetPrice> collect = netPrices.stream()
            .filter(netPrice -> {
                String specification = netPrice.getSpecification();
                if (specification.contains(SPLIT_LINE)) {
                    List<String> specificInterval = getSpecificInterval(specification);
                    return specificInterval.stream().anyMatch(s -> query.getSpecification().contains(s));
                } else {
                    return query.getSpecification().contains(specification);
                }
            }).collect(Collectors.toList());
        return collect.stream().map(netPrice -> getVO(netPrice, "yyyy-MM-dd HH:mm:ss")).collect(Collectors.toList());
    }

    /**
     * 计算规格区间的所有规格
     *
     * @param specification 规格区间: Ф7-Ф9   <--> Ф7,Ф8,Ф9
     * @return result
     */
    private List<String> getSpecificInterval(String specification) {

        ArrayList<String> specific = new ArrayList<>();
        List<String> specificInterval = Arrays.asList(StringUtils.replace(specification, " ", "").substring(1).split("-"));
        BigDecimal num1 = new BigDecimal(specificInterval.get(0));
        BigDecimal num2 = new BigDecimal(specificInterval.get(1));
        BigDecimal min = num1.min(num2);
        BigDecimal max = num1.max(num2);
        specific.add(FI.concat(min.toString()));
        while (true) {
            min = min.add(new BigDecimal("0.5"));
            if (min.compareTo(max) > 0) {
                break;
            }
            String fi = FI.concat(min.toString());
            fi = fi.replaceAll("0+$", "");

            if (fi.endsWith(".")) {
                fi = fi.substring(0, fi.length() - 1);
            }
            specific.add(fi);
        }

        log.info("........................................... specific list : " + specific + " ...........................................");

        return specific;
    }
//
//    /**
//     * 获取详情
//     *
//     * @param steelChangeRow 变更记录
//     * @return result
//     */
//    private List<SteelDetail> doDetail(SteelChangeRow steelChangeRow) {
//        log.info("........................................... doDetail ...........................................");
//
//        if (steelChangeRow == null) {
//            return Collections.emptyList();
//        }
//        ArrayList<SteelDetail> steelDetails = new ArrayList<>();
//        List<Market> markets = steelChangeRow.getMarkets();
//        if (!markets.isEmpty()) {
//            markets.forEach(market -> {
//                //获取变更详情
//                if (StringUtils.hasText(market.getUrl())) {
//                    Response response = CommonApiClient.generateGetRequestAndGetResponse(market.getUrl());
//                    if (String.valueOf(response.code()).startsWith(String.valueOf(HttpStatus.Series.SUCCESSFUL.value()))) {
//                        SteelDetail steelDetail = ResponseParseUtil.parseResponse2Obj(response, SteelDetail.class);
//                        if (steelDetail != null) {
//                            steelDetails.add(steelDetail);
//                        }
//                    }
//                }
//            });
//        }
//        return steelDetails;
//    }


    public NetPriceDTO getVO(BaseNetPrice baseNetPrice) {
        return new NetPriceDTO(baseNetPrice);
    }

    /**
     * **
     *
     * @param baseNetPrice 网价数据
     * @param dataFormat   时间格式化字符串
     * @return result
     */
    public NetPriceDTO getVO(BaseNetPrice baseNetPrice, String dataFormat) {
        return new NetPriceDTO(baseNetPrice, dataFormat);
    }
}

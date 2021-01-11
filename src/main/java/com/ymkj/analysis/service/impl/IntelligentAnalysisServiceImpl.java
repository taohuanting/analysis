package com.ymkj.analysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.*;
import com.ymkj.analysis.entity.dto.*;
import com.ymkj.analysis.entity.query.AnalysisQuery;
import com.ymkj.analysis.entity.query.BaseQuery;
import com.ymkj.analysis.entity.query.NetPriceQuery;
import com.ymkj.analysis.repository.BaseNetPriceMapper;
import com.ymkj.analysis.service.IntelligentAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 网价业务实现
 *
 * @author tao
 */
@Slf4j
@Service
public class IntelligentAnalysisServiceImpl implements IntelligentAnalysisService {
    private static final String GAOXIAN = "高线";
    private static final String PANYUAN = "盘圆";
    private static final List<String> GAOXIAO_PANYUAN = Arrays.asList("高线", "盘圆");
    private static final Integer TWO = 2;
    private static final String FI = "Ф";
    private static final String SPLIT_LINE = "-";


    @Autowired
    private BaseNetPriceMapper netPriceMapper;

    @Override
    public Page<NetPriceMaterial> getMaterialList(BaseQuery query) {
        netPriceMapper.selectPage(new Page<>(query.getPage(),query.getPageSize()),new LambdaQueryWrapper<BaseNetPrice>());
        return null;
    }

    @Override
    public List<String> getManufacturerList(String cityName) {
        return netPriceMapper.selectManufacturerList(cityName);
    }

    @Override
    public BaseNetPrice getNetPriceByMaterialAndDate(AnalysisQuery query) {
        return null;
    }

    @Override
    public List<BaseNetPrice> getOtherNetPriceByMaterial(AnalysisQuery query) {
        return null;
    }

    @Override
    public Map<String, SameManufacturerNetPriceDTO> compare(AnalysisQuery query) {
        return null;
    }

//

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
}

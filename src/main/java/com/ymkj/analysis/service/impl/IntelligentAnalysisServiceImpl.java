package com.ymkj.analysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.*;
import com.ymkj.analysis.entity.dto.*;
import com.ymkj.analysis.entity.query.AnalysisQuery;
import com.ymkj.analysis.entity.query.BaseQuery;
import com.ymkj.analysis.entity.query.NetPriceQuery;
import com.ymkj.analysis.repository.BaseNetPriceMapper;
import com.ymkj.analysis.service.IntelligentAnalysisService;
import com.ymkj.analysis.utils.BeanMapper;
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
        Page<BaseNetPrice> page = netPriceMapper.selectPage(new Page<>(query.getPage(), query.getPageSize()),
                new QueryWrapper<BaseNetPrice>()
                        .select("DISTINCT `material_name_`", "material_", "specification_", "unit_")
                .orderByAsc("material_name_")
                .orderByAsc("material_")
                .orderByAsc("specification_")
        );
        Page<NetPriceMaterial> result = new Page<>();
        List<BaseNetPrice> records = page.getRecords();
        page.setRecords(null);
        BeanMapper.mapIgnoreEmpty(page,result);
        result.setRecords(BeanMapper.mapListIgnoreEmpty(records,NetPriceMaterial.class));
        return result;
    }

    @Override
    public List<String> getManufacturerList(String cityName) {
        return netPriceMapper.selectManufacturerList(cityName);
    }

    @Override
    public BaseNetPrice getNetPriceByMaterialAndDate(AnalysisQuery query) {
        return netPriceMapper.selectOne(new LambdaQueryWrapper<BaseNetPrice>()
                .eq(StringUtils.hasText(query.getMaterialName()),BaseNetPrice::getMaterialName,query.getMaterialName())
                .eq(StringUtils.hasText(query.getMaterial()),BaseNetPrice::getMaterial,query.getMaterial())
                .eq(StringUtils.hasText(query.getSpecification()),BaseNetPrice::getSpecification,query.getSpecification())
                .eq(StringUtils.hasText(query.getArea()),BaseNetPrice::getArea,query.getArea())
                .eq(StringUtils.hasText(query.getManufacturer()),BaseNetPrice::getManufacturer,query.getManufacturer())
                .orderByDesc(BaseNetPrice::getPublishTime)
                .last("limit 1")
        );
    }

    @Override
    public List<BaseNetPrice> getOtherNetPriceByMaterial(AnalysisQuery query) {
        return null;
    }

    @Override
    public Map<String, SameManufacturerNetPriceDTO> compare(AnalysisQuery query) {
        return null;
    }

}

package com.ymkj.analysis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.BaseNetPrice;
import com.ymkj.analysis.entity.dto.NetPriceMaterial;
import com.ymkj.analysis.entity.dto.SameManufacturerNetPriceDTO;
import com.ymkj.analysis.entity.query.AnalysisQuery;
import com.ymkj.analysis.entity.query.BaseQuery;
import com.ymkj.analysis.service.IntelligentAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName NetPriceAnalysis
 * @Author tao
 * @Date 2021/1/11
 **/
@Api(tags = "网价智能分析API")
@RestController
@RequestMapping("/net-price")
public class IntelligentAnalysis {

    @Autowired
    private IntelligentAnalysisService intelligentAnalysisService;

    @GetMapping("/material-list")
    @ApiOperation(value = "获取材料列表", notes = "支持分页")
    public Page<NetPriceMaterial> getMaterialList(BaseQuery query) {
        return intelligentAnalysisService.getMaterialList(query);
    }

    @GetMapping("/Manufacturer/{cityName}")
    @ApiOperation(value = "根据城市名称获取厂家列表", notes = "不支持分页")
    public List<String> getManufacturerList(@PathVariable String cityName) {
        return intelligentAnalysisService.getManufacturerList(cityName);
    }

    @GetMapping("/net-price")
    @ApiOperation(value = "获取单个物料最新网价", notes = "必传：物料（名称，材质，规格），城市，厂家")
    public BaseNetPrice getNetPriceByMaterialAndDate(AnalysisQuery query) {
        return intelligentAnalysisService.getNetPriceByMaterialAndDate(query);
    }

    @PostMapping("/net-price/other-three")
    @ApiOperation(value = "获取单个物料最新网价-其它3个厂家", notes = "必传：物料（名称，材质，规格）")
    public List<BaseNetPrice> getOtherNetPriceByMaterial(@RequestBody AnalysisQuery query) {
        return intelligentAnalysisService.getOtherNetPriceByMaterial(query);
    }

    @PostMapping("/net-price/compare")
    @ApiOperation(value = "物料网价对比", notes = "必传：物料（名称，材质，规格）")
    public Map<String, SameManufacturerNetPriceDTO> compare(@RequestBody AnalysisQuery query) {
        return intelligentAnalysisService.compare(query);
    }
}

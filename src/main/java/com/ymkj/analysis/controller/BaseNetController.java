package com.ymkj.analysis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.service.BaseNetPriceService;
import com.ymkj.analysis.service.query.NetPriceQuery;
import com.ymkj.analysis.service.vo.NetPriceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BaseNetController
 * @Author tao
 * @Date 2021/1/7
 **/
@Api(tags = "网价API")
@RestController
@RequestMapping(value = "/net-price")
public class BaseNetController {
    @Autowired
    private BaseNetPriceService baseNetPriceService;

    @GetMapping
    @ApiOperation(value = "条件分页查询网价")
    public Page<NetPriceVO> getPage(NetPriceQuery query) {
        return baseNetPriceService.listWithPage(query);
    }

//物料（名称，材质，规格）  时间  城市 厂家

    @GetMapping("/same-time")
    @ApiOperation(value = "同一物料、时间，指定城市内价格查询")
    public Page<NetPriceVO> sameTime(NetPriceQuery query) {
        handleMaterial(query);
        return baseNetPriceService.listWithPage(query);
    }

    @GetMapping("/same-area")
    @ApiOperation(value = "同一物料、城市，指定时间段内价格查询")
    public Page<NetPriceVO> sameArea(NetPriceQuery query) {
        handleMaterial(query);
        return baseNetPriceService.listWithPage(query);
    }

    @GetMapping("/same-manufacturer")
    @ApiOperation(value = "同一物料、厂家，指定时间段内价格查询")
    public Page<NetPriceVO> sameManufacturer(NetPriceQuery query) {
        handleMaterial(query);
        return baseNetPriceService.listWithPage(query);
    }
    /**
     * 处理物料 物料名称-材质-规格
     * @param query
     * @return void
     **/
    private void handleMaterial(NetPriceQuery query){
        String material = query.getMaterial();
        String[] split = material.split("-");
        query.setMaterialName(split[0]);
        query.setMaterialMaterial(split[1]);
        query.setMaterialSpec(split[2]);
    }
}

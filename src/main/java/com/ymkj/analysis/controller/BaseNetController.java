package com.ymkj.analysis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.BaseNetPrice;
import com.ymkj.analysis.entity.query.NetPriceQuery;
import com.ymkj.analysis.service.BaseNetPriceService;
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
    public Page<BaseNetPrice> getPage(NetPriceQuery query) {
        return baseNetPriceService.getPage(query);
    }

//物料（名称，材质，规格）  时间  城市 厂家

    @GetMapping("/same-time")
    @ApiOperation(value = "同一物料、时间，各城市价格查询", notes = "取各城市当天先发布的")
    public Page<BaseNetPrice> sameTime(NetPriceQuery query) {
        return baseNetPriceService.getPageBySameTime(query);
    }

    @GetMapping("/same-area")
    @ApiOperation(value = "同一物料、城市，指定时间段内价格查询", notes = "取各城市当天先发布的")
    public Page<BaseNetPrice> sameArea(NetPriceQuery query) {
        return baseNetPriceService.getPageBySameArea(query);
    }

    @GetMapping("/same-manufacturer")
    @ApiOperation(value = "同一物料、厂家，指定时间段内价格查询", notes = "取每天第一次发布的")
    public Page<BaseNetPrice> sameManufacturer(NetPriceQuery query) {
        return baseNetPriceService.getPageBySameManufacturer(query);
    }

}

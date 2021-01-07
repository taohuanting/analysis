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

}

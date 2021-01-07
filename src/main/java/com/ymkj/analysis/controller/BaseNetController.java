package com.ymkj.analysis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.domain.BaseNetPrice;
import com.ymkj.analysis.service.BaseNetPriceService;
import com.ymkj.analysis.service.query.NetPriceQuery;
import com.ymkj.analysis.service.vo.NetPriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BaseNetController
 * @Author tao
 * @Date 2021/1/7
 **/
@RestController
public class BaseNetController {
    @Autowired
    private BaseNetPriceService baseNetPriceService;
    @GetMapping
    public Page<NetPriceVO> getPage(NetPriceQuery query){
        return baseNetPriceService.listWithPage(query);
    }
}

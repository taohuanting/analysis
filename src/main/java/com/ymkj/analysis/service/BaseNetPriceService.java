package com.ymkj.analysis.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.BaseNetPrice;
import com.ymkj.analysis.entity.dto.*;
import com.ymkj.analysis.entity.query.BaseQuery;
import com.ymkj.analysis.entity.query.NetPriceQuery;
import com.ymkj.analysis.entity.query.PikeNetPriceQuery;

import java.util.List;

/**
 * 网价管理业务接口
 *
 * @author tao
 */
public interface BaseNetPriceService  {
    /**
     * 分页查询-包装了分页对象
     *
     * @param query 查询条件对象
     * @return result
     */
    Page<BaseNetPrice> getPage(NetPriceQuery query);

    /**
     * 获取网价数据
     */
    void getNetPriceDate();

    /**
     * 查询物料名称列表
     *
     * @return result
     */
    List<String> getMaterialNameList();

    /**
     * 获取网价来源列表
     *
     * @return result
     */
    List<NetPriceSourceDTO> getSource();

    /**
     * 查询网价信息
     *
     * @param netPriceQuery 查询条件表单
     * @return result
     */
    List<PikeNetPriceDTO> selectPikeNetPrice(List<PikeNetPriceQuery> netPriceQuery);

    SameTimeNetPriceDTO getSameTimeList(NetPriceQuery query);

    SameAreaNetPriceDTO getSameAreaList(NetPriceQuery query);

    SameManufacturerNetPriceDTO getSameManufacturerList(NetPriceQuery query);

}

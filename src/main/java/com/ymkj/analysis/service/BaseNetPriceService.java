package com.ymkj.analysis.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.service.query.NetPriceQuery;
import com.ymkj.analysis.service.query.PikeNetPriceQuery;
import com.ymkj.analysis.service.vo.NetPriceSourceVO;
import com.ymkj.analysis.service.vo.NetPriceVO;
import com.ymkj.analysis.service.vo.PikeNetPriceVO;

import java.util.List;

/**
 * 网价管理业务接口
 *
 * @author wkn
 */
public interface BaseNetPriceService  {
    /**
     * 分页查询-包装了分页对象
     *
     * @param query 查询条件对象
     * @return result
     */
    Page<NetPriceVO> listWithPage(NetPriceQuery query);

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
    List<NetPriceSourceVO> getSource();

    /**
     * 查询网价信息
     *
     * @param netPriceQuery 查询条件表单
     * @return result
     */
    List<PikeNetPriceVO> selectPikeNetPrice(List<PikeNetPriceQuery> netPriceQuery);

}

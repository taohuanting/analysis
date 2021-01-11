package com.ymkj.analysis.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ymkj.analysis.entity.domain.BaseNetPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tao
 */
@Mapper
public interface BaseNetPriceMapper extends BaseMapper<BaseNetPrice> {

    /**
     * 根据钢铁网网价信息查询本地网价信息
     *
     * @param netPrice 钢铁网发布网价信息
     * @return result
     */
    List<BaseNetPrice> selectNetPriceByCon(BaseNetPrice netPrice);

    /**
     * 查询物料名称列表
     *
     * @return result
     */
    List<String> selectMaterialNameList();

    /**
     * 查询库中是否有该城市指定日期网价数据
     *
     * @param area      城市
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 数量
     */
    Integer selectNetPriceCount(@Param("area") String area, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询网价
     *
     * @param area             城市
     * @param material         材质
     * @param materialNames    物料名称
     * @param manufacturerList 厂商
     * @param startDate        开始时间
     * @param endDate          结束时间
     * @return result
     */
    List<BaseNetPrice> selectNetPriceList(@Param("area") String area, @Param("material") String material, @Param("materialNames") List<String> materialNames, @Param("manufacturerList") List<String> manufacturerList, @Param("startDate") String startDate, @Param("endDate") String endDate);
}

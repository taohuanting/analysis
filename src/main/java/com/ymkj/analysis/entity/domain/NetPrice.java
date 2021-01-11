package com.ymkj.analysis.entity.domain;

import lombok.Data;

/**
 * @author tao
 */
@Data
public class NetPrice {
    /**
     * 备注
     */
    private String note;
    /**
     * 单位
     */
    private String unit;
    /**
     * 材质
     */
    private String material;
    /**
     * 价格（元/吨）
     */
    private String price;
    /**
     * 涨跌
     */
    private String raise;
    /**
     * 钢厂/产地
     */
    private String place;
    /**
     * 品类
     */
    private String category;
    /**
     * 品名
     */
    private String breed;
    /**
     * 规格（mm）
     */
    private String spec;
    /**
     * rowId
     */
    private String rowId;
    /**
     * tableRowId
     */
    private String tableRowId;

    /**
     * 月均价
     */
    private String avgmonthpri;
    /**
     * 30日均价
     */
    private String avgpri30;
    /**
     * 10日均价
     */
    private String avgpri10;
    /**
     * 7日均价
     */
    private String avgpri7;
}

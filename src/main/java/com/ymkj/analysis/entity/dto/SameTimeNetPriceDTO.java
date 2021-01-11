package com.ymkj.analysis.entity.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 网价视图对象
 *
 * @author tao
 */
@Data
@ApiModel(value = "同一物料同一时间不同地区网价视图")
public class SameTimeNetPriceDTO implements Serializable {

    @ApiModelProperty("物料")
    private NetPriceMaterial material;

    @ApiModelProperty("发布时间")
    private String publishDate;

    @ApiModelProperty("地区价格集合")
    private List<AreaPrice> areaPriceList;


}

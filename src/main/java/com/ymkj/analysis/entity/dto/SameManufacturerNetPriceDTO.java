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
@ApiModel(value = "同一物料同一厂家不同时间网价视图")
public class SameManufacturerNetPriceDTO implements Serializable {

    @ApiModelProperty("物料")
    private NetPriceMaterial material;

    @ApiModelProperty("厂家")
    private String manufacturer;

    @ApiModelProperty("区域")
    private String area;

    @ApiModelProperty("发布时间价格集合")
    private List<TimePrice> timePriceList;
}

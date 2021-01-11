package com.ymkj.analysis.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName AreaPrice
 * @Author tao
 * @Date 2021/1/11
 **/
@Data
@ApiModel("地区价格集合")
public class AreaPrice{

    @ApiModelProperty("区域")
    private String area;

    @ApiModelProperty("厂商")
    private String manufacturer;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty("日环比涨跌")
    private String fluctuate;
}

package com.ymkj.analysis.entity.dto;

import com.ymkj.analysis.entity.domain.BaseNetPrice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName TimePrice
 * @Author tao
 * @Date 2021/1/11
 **/
@Data
@ApiModel("发布时间价格视图")
public  class TimePrice {

    @ApiModelProperty("发布时间")
    private Date publishDate;

    @ApiModelProperty("厂商")
    private String manufacturer;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty("日环比涨跌")
    private String fluctuate;

    public TimePrice(BaseNetPrice netPrice) {
        this.publishDate = netPrice.getPublishTime();
        this.manufacturer = netPrice.getManufacturer();
        this.price = netPrice.getPrice();
        this.fluctuate = netPrice.getRaise();
    }

    public TimePrice() {
    }
}

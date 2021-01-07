package com.ymkj.analysis.service.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 网价来源
 * @author wkn
 */
@Data
@ApiModel(value = "网价来源")
public class NetPriceSourceVO {
    @ApiModelProperty("数据库存储值")
    private Integer value;
    @ApiModelProperty("名称")
    private String name;

    public NetPriceSourceVO() {
    }

    public NetPriceSourceVO(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}

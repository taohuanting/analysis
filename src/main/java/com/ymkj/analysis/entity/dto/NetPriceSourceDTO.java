package com.ymkj.analysis.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 网价来源
 * @author tao
 */
@Data
@ApiModel(value = "网价来源")
public class NetPriceSourceDTO {
    @ApiModelProperty("数据库存储值")
    private Integer value;
    @ApiModelProperty("名称")
    private String name;

    public NetPriceSourceDTO() {
    }

    public NetPriceSourceDTO(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}

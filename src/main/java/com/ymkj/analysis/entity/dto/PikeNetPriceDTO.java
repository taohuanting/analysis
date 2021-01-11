package com.ymkj.analysis.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author tao
 */
@Data
public class PikeNetPriceDTO {
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称", required = true)
    private String materialName;
    /**
     * 材质
     */
    @ApiModelProperty(value = "材质", required = true)
    private String material;
    /**
     * 规格
     */
    @ApiModelProperty(value = "规格", required = true)
    private List<String> specification;
    /**
     * 厂商
     */
    @ApiModelProperty(value = "厂商", required = true)
    private List<String> manufacturer;
    /**
     * 地区
     */
    @ApiModelProperty(value = "地区", required = true)
    private String area;
    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期", required = true)
    private String publishTime;
    /**
     * 网价列表
     */
    @ApiModelProperty(value = "网价列表", required = true)
    private List<NetPriceDTO> netPrices;
}

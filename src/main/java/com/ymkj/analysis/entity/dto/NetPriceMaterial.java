package com.ymkj.analysis.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName NetPriceMaterial
 * @Author tao
 * @Date 2021/1/11
 **/
@Data
@ApiModel("网价物料视图")
public class NetPriceMaterial {
    /**
     * 物料编码
     */
    @ApiModelProperty("物料编码")
    private String materialCode;
    /**
     * 物料名称
     */
    @ApiModelProperty("物料名称")
    private String materialName;
    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String specification;
    /**
     * 材质
     */
    @ApiModelProperty("材质")
    private String texture;
    /**
     * 定尺
     */
    @ApiModelProperty("定尺")
    private String sizingName;
    /**
     * 单位
     */
    @ApiModelProperty("单位")
    private String unit;
}

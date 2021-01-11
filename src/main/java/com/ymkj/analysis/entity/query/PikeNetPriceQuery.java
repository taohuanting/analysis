package com.ymkj.analysis.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 功能描述： 网价查询条件参数
 *
 * @author: wkn
 * @date create in 20-3-5 下午3:09
 */
@Data
public class PikeNetPriceQuery {
    @ApiModelProperty(value = "物料名称", required = true)
    private String materialName;
    @ApiModelProperty(value = "材质", required = true)
    private String material;
    @ApiModelProperty(value = "规格", required = true)
    private List<String> specification;
    @ApiModelProperty(value = "厂商", required = true)
    private List<String> manufacturer;
    @ApiModelProperty(value = "地区", required = true)
    private String area;
    @ApiModelProperty(value = "发布日期", required = true)
    private String publishTime;
}

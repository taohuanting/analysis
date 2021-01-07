package com.ymkj.analysis.service.query;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 网价高级条件分页查询条件类
 *
 * @author wkn
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "网价查询条件")
public class NetPriceQuery extends BaseQuery {
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料Id,逗号分割")
    private String materialIds;
    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称，数组")
    private List<String> materialNames;
    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private String publishDate;
    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String area;
}

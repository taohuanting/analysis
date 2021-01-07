package com.ymkj.analysis.service.query;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
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

    @ApiModelProperty(value = "物料Id,逗号分割")
    private String materialIds;

    @ApiModelProperty(value = "物料(物料名称-材质-规格)")
    private String material;

    @ApiModelProperty(value = "物料-名称")
    private String materialName;

    @ApiModelProperty(value = "物料-材质")
    private String materialMaterial;

    @ApiModelProperty(value = "物料-规格")
    private String materialSpec;

    @ApiModelProperty(value = "物料名称，数组")
    private List<String> materialNames;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "区域，list")
    private List<String> areas;

    @ApiModelProperty(value = "厂家名称")
    private String manufacturer;

    @ApiModelProperty(value = "厂家名称，list")
    private List<String> manufacturers;

    @ApiModelProperty(value = "发布时间")
    private Date publishDate;

    @ApiModelProperty(value = "发布时间，起始")
    private Date publishDateStart;

    @ApiModelProperty(value = "发布时间，截止")
    private Date publishDateEnd;
}

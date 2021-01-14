package com.ymkj.analysis.entity.query;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 网价高级条件分页查询条件类
 *
 * @author tao
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "网价智能分析查询条件")
public class AnalysisQuery extends BaseQuery {

    @ApiModelProperty(value = "物料-名称")
    private String materialName;

    @ApiModelProperty(value = "物料-材质")
    private String material;

    @ApiModelProperty(value = "物料-规格")
    private String specification;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "厂家名称")
    private String manufacturer;

    @ApiModelProperty(value = "发布时间，起始")
    private Date publishDateStart;

    @ApiModelProperty(value = "发布时间，截止")
    private Date publishDateEnd;

    @ApiModelProperty(value = "对比-城市、厂家")
    private List<Compare> compareList;

    @Data
    @ApiModel("网价对比参数-城市、厂家")
    public static class Compare{

        @ApiModelProperty(value = "区域")
        private String area;

        @ApiModelProperty(value = "厂家名称")
        private String manufacturer;

    }
}

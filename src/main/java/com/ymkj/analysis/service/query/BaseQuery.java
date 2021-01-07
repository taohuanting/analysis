package com.ymkj.analysis.service.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础查询条件类
 *
 * @author wkn
 */
@Data
@ApiModel
public class BaseQuery {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    /**
     * 状态 0-草稿，1-启用，-1-禁用，不传-全部
     */
    @ApiModelProperty("状态 0-草稿，1-启用，-1-禁用，不传-全部")
    private Integer status;
}

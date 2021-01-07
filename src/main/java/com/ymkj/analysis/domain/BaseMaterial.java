package com.ymkj.analysis.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 物料信息实体类
 *
 * @author wkn
 */
@Data
@TableName("t_base_material")
public class BaseMaterial {

    @TableId(value = "id_",type = IdType.AUTO)
    private Integer id;

    /**
     * 物料分类ID
     */
    @TableField(value = "sys_category_id_")
    private Integer sysCategoryId;

    /**
     * 编码
     */
    @TableField(value = "code_")
    private String code;

    /**
     * 名称
     */
    @TableField(value = "name_")
    private String name;

    /**
     * 规格
     */
    @TableField(value = "specification_")
    private String specification;

    /**
     * 材质
     */
    @TableField(value = "material_")
    private String material;

    /**
     * 单位ID
     */
    @TableField(value = "unit_")
    private Integer unit;

    /**
     * 备注
     */
    @TableField(value = "remark_")
    private String remark;

    @TableField(value = "old_id_")
    private String oldId;

    /**
     * 状态
     */
    @TableField(value = "status_")
    private Integer status;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id_")
    private String tenantId;
    /**
     * 创建人
     */
    @TableField(value = "create_by_")
    private String createBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by_")
    private String updateBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time_")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time_")
    private Date updateTime;

}

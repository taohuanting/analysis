package com.ymkj.analysis.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * base_area 行政区域实体类
 *
 * @author tao
 */
@Data
@TableName(value = "t_base_area")
public class BaseArea implements Serializable {
    /**
     * 唯一逐渐UUID
     */
    @TableId(value = "id_",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 序号
     */
    @TableField(value = "serial_num_")
    private Long serialNum;

    /**
     * 编码
     */
    @TableField(value = "code_")
    private String code;

    /**
     * 集采1.0code
     */
    @TableField(value = "code_sync_")
    private String codeSync;

    /**
     * 区域类型
     */
    @TableField(value = "area_type_")
    private Integer areaType;

    /**
     * 名称
     */
    @TableField(value = "name_")
    private String name;

    /**
     * 父ID
     */
    @TableField(value = "parent_id_")
    private String parentId;

    /**
     * 父ID集合
     */
    @TableField(value = "parent_ids_")
    private String parentIds;

    /**
     * 状态 0、草稿，1、启用，-1、禁用，2-删除
     */
    @TableField(value = "status_")
    private Integer status;

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

    @TableField(value = "create_time_")
    private Date createTime;

    @TableField(value = "update_time_")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}

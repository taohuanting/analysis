package com.ymkj.analysis.entity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_base_steel网价实体类
 *
 * @author tao
 */
@Data
@TableName(value = "t_base_net_price")
public class BaseNetPrice implements Serializable {
    /**
     * 唯一主键UUID
     */
    @TableId(value = "id_",type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 序号
     */
    @TableField(value = "serial_num_")
    private Long serialNum;

    /**
     * 物料名称
     */
    @TableField(value = "material_name_")
    private String materialName;

    /**
     * 厂商
     */
    @TableField(value = "manufacturer_")
    private String manufacturer;

    /**
     * 品类
     */
    @TableField(value = "category_")
    private String category;

    /**
     * 材质
     */
    @TableField(value = "material_")
    private String material;

    /**
     * 备注
     */
    @TableField(value = "note_")
    private String note;

    /**
     * 钢厂/产地
     */
    @TableField(value = "area_")
    private String area;

    /**
     * 价格（元/吨）
     */
    @TableField(value = "price_")
    private String price;

    /**
     * 涨跌
     */
    @TableField(value = "raise_")
    private String raise;

    /**
     * 规格（mm）
     */
    @TableField(value = "specification_")
    private String specification;

    /**
     * 单位
     */
    @TableField(value = "unit_")
    private String unit;

    /**
     * rowId
     */
    @TableField(value = "row_id_")
    private String rowId;

    /**
     * 0:否 1是
     */
    @TableField(value = "delete_")
    private Byte delete;

    /**
     * 发布时间
     */
    @TableField(value = "publish_time_")
    private Date publishTime;

    @TableField(value = "title_")
    private String title;

    /**
     * 网价来源:1-我的钢铁网；2-兰格钢铁网；3-有色钢铁网
     */
    @TableField(value = "source_")
    private Integer source;

    /**
     * 表单id,与城市对应
     */
    @TableField(value = "table_id_")
    private String tableId;

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

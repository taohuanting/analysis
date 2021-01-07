package com.ymkj.analysis.service.vo;


import com.ymkj.analysis.domain.BaseMaterial;
import com.ymkj.analysis.domain.BaseNetPrice;
import com.ymkj.analysis.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 网价视图对象
 *
 * @author wkn
 */
@Data
@ApiModel(value = "网价")
public class NetPriceVO implements Serializable {
    /**
     * 唯一主键UUID
     */
    @ApiModelProperty("唯一主键UUID")
    private String id;
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

    /**
     * 厂商
     */
    @ApiModelProperty("厂商")
    private String manufacturer;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private String price;

    /**
     * 日环比涨跌
     */
    @ApiModelProperty("日环比涨跌")
    private String fluctuate;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private String publishDate;

    /**
     * 区域
     */
    @ApiModelProperty("区域")
    private String area;

    public NetPriceVO(BaseNetPrice baseNetPrice) {
        this.id = baseNetPrice.getId();
        this.materialName = baseNetPrice.getMaterialName();
        this.specification = baseNetPrice.getSpecification();
        this.texture = baseNetPrice.getMaterial();
        this.unit = baseNetPrice.getUnit();
        this.manufacturer = baseNetPrice.getManufacturer();
        this.price = baseNetPrice.getPrice();
        this.fluctuate = baseNetPrice.getRaise();
        this.publishDate = DateUtil.formatDate(baseNetPrice.getPublishTime(),"yyyy-MM-dd");
        this.area = baseNetPrice.getArea();
    }
    public NetPriceVO(BaseNetPrice baseNetPrice, String dataFormat) {
        this.id = baseNetPrice.getId();
        this.materialName = baseNetPrice.getMaterialName();
        this.specification = baseNetPrice.getSpecification();
        this.texture = baseNetPrice.getMaterial();
        this.unit = baseNetPrice.getUnit();
        this.manufacturer = baseNetPrice.getManufacturer();
        this.price = baseNetPrice.getPrice();
        this.fluctuate = baseNetPrice.getRaise();
        this.publishDate = DateUtil.formatDate(baseNetPrice.getPublishTime(),dataFormat);
        this.area = baseNetPrice.getArea();
    }

    public void setMaterial(BaseMaterial baseMaterial) {
        this.materialCode = baseMaterial.getCode();
        this.materialName = baseMaterial.getName();
        this.sizingName = baseMaterial.getSpecification();
        this.texture = baseMaterial.getMaterial();
    }

}

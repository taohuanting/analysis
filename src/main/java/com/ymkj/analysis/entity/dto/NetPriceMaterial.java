package com.ymkj.analysis.entity.dto;

import com.ymkj.analysis.entity.domain.BaseNetPrice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName NetPriceMaterial
 * @Author tao
 * @Date 2021/1/11
 **/
@Data
@ApiModel("网价物料视图")
public class NetPriceMaterial {

    @ApiModelProperty("物料名称")
    private String materialName;

    @ApiModelProperty("材质")
    private String material;

    @ApiModelProperty("规格")
    private String specification;

    @ApiModelProperty("单位")
    private String unit;

    public NetPriceMaterial(BaseNetPrice netPrice) {
        this.materialName = netPrice.getMaterialName();
        this.material = netPrice.getMaterial();
        this.specification = netPrice.getSpecification();
        this.unit = netPrice.getUnit();
    }

    public NetPriceMaterial() {
    }
}

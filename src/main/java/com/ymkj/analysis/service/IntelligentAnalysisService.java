package com.ymkj.analysis.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymkj.analysis.entity.domain.BaseNetPrice;
import com.ymkj.analysis.entity.dto.*;
import com.ymkj.analysis.entity.query.AnalysisQuery;
import com.ymkj.analysis.entity.query.BaseQuery;

import java.util.List;
import java.util.Map;

/**
 * 网价管理业务接口
 *
 * @author tao
 */
public interface IntelligentAnalysisService {
    Page<NetPriceMaterial> getMaterialList(BaseQuery query);

    List<String> getManufacturerList(String cityName);

    BaseNetPrice getNetPriceByMaterialAndDate(AnalysisQuery query);

    List<BaseNetPrice> getOtherNetPriceByMaterial(AnalysisQuery query);

    Map<String, SameManufacturerNetPriceDTO> compare(AnalysisQuery query);
}

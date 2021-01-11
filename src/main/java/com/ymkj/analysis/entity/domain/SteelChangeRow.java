package com.ymkj.analysis.entity.domain;


import lombok.Data;

import java.util.List;

/**
 * 钢铁网变更记录
 *
 * @author tao
 */
@Data
public class SteelChangeRow {
    private Integer totalRowCount;
    private Integer maxPageRowCount;
    private Integer currentPageNum;
    private List<Market> markets;

}

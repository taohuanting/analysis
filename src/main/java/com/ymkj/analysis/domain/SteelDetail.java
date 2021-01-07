package com.ymkj.analysis.domain;

import lombok.Data;

import java.util.List;

/**
 * 钢铁网返回的数据对象
 * @author wkn
 */
@Data
public class SteelDetail {
    private String publishTime;
    private String date;
    private String title;
    private String time;
    private NetPrice heads;
    private List<NetPrice> contents;
}

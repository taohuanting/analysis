package com.ymkj.analysis.entity.enums;



import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网价来源
 *
 * @author tao
 */
public enum SteelCityCode {
    /**
     * 重庆
     */
    CHONGQIN(15778, "重庆"),
    /**
     * 成都
     */
    CHENGDU(15757, "成都"),
    /**
     * 贵阳
     */
    GUIYANG(15762, "贵阳"),
    /**
     * 昆明
     */
    KUNMING(15768, "昆明"),
    /**
     * 广州
     */
    GUANGZHOU(15584, "广州"),
    /**
     * 深圳
     */
    SHENZHEN(15609, "深圳"),
    /**
     * 南宁
     */
    LANNING(15605, "南宁"),
    /**
     * 上海
     */
    SHANGHAI(15278, "上海"),
    /**
     * 苏州
     */
    SUZHOU(15420, "苏州"),
    /**
     * 西安
     */
    XIAN(15738, "西安"),
    /**
     * 绵阳
     */
    MIANYANG(15773, "绵阳"),
    /**
     * 西昌
     */
    XICHANG(80661, "西昌"),
    /**
     * 南京
     */
    LANJING(15407, "南京"),
    /**
     * 徐州
     */
    XUZHOU(15440, "徐州"),
    /**
     * 南昌
     */
    LANCHANG(15399, "南昌"),
    /**
     * 银川
     */
    YINCHUAN(15747, "银川"),
    /**
     * 武汉
     */
    WUHAN(15346, "武汉");

    protected static final Map<Integer, String> MAP_CODE_VALUE = new LinkedHashMap<>();
    protected static final Map<String, Integer> MAP_VALUE_CODE = new LinkedHashMap<>();

    static {
        for (SteelCityCode e : SteelCityCode.values()) {
            MAP_CODE_VALUE.put(e.code, e.value());
            MAP_VALUE_CODE.put(e.value(), e.code);
        }
    }

    private String value() {
        return this.value;
    }

    public static String get(Integer code) {
        return MAP_CODE_VALUE.get(code);
    }

    public static Integer getCityId(String cityName) {
        return MAP_VALUE_CODE.get(cityName);
    }


    private final int code;
    private final String value;

    private SteelCityCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer toInteger() {
        return this.code;
    }
}

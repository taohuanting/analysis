package com.ymkj.analysis.utils.enums;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网价来源
 *
 * @author wkn
 */
public enum NetPriceSource {
    /**
     * 我的钢铁网
     */
    MY_STEEL(1, "我的钢铁网"),
    /**
     * 兰格钢铁网
     */
    LG_STEEL(2, "兰格钢铁网"),
    /**
     * 有色钢铁网
     */
    YS_STEEL(3, "有色钢铁网");

    protected static final Map<Integer, String> MAP = new LinkedHashMap<>();

    static {
        for (NetPriceSource e : NetPriceSource.values()) {
            MAP.put(e.code, e.value());
        }
    }

    private String value() {
        return this.value;
    }

    public static String get(Integer code) {
        return MAP.get(code);
    }

    private final int code;
    private final String value;

    private NetPriceSource(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer toInteger() {
        return this.code;
    }
}

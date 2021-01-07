package com.ymkj.analysis.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wkn
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.hxyc.mysteel")
public class SteelConfigEntity {
    private String token;
    private String gatherUrl;
    private String detailUrl;
    private List<Integer> tableIds;
}

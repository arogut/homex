package com.arogut.homex.edge.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "edge")
@Component
@Getter
@Setter
public class EdgeProperties {
    private long publishDelay;
    private long publishPeriod;
}

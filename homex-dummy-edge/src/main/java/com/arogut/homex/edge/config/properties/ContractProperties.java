package com.arogut.homex.edge.config.properties;

import com.arogut.homex.edge.model.MeasurementType;
import com.arogut.homex.edge.model.NumberMeasurement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "edge.contract")
@Component
@Getter
@Setter
public class ContractProperties {
    private Map<MeasurementType, List<NumberMeasurement>> measurements;
}

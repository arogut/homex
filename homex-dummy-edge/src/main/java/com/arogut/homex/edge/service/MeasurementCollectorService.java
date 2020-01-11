package com.arogut.homex.edge.service;

import com.arogut.homex.edge.config.properties.ContractProperties;
import com.arogut.homex.edge.model.Measurement;
import com.arogut.homex.edge.model.MeasurementType;
import com.arogut.homex.edge.model.NumberMeasurement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementCollectorService {
    private final ContractProperties contractProperties;

    public List<Measurement> getMeasurement() {
        return contractProperties.getMeasurements().entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream()
                        .map(s -> new AbstractMap.SimpleImmutableEntry<>(e.getKey(), s)))
                .map(this::generateValue)
                .collect(Collectors.toList());
    }

    private NumberMeasurement generateValue(Map.Entry<MeasurementType, NumberMeasurement> entry) {
        return entry.getKey().getGenerator().generateValue(entry.getValue());
    }

}

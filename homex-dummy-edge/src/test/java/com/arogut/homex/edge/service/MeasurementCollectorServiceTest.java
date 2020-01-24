package com.arogut.homex.edge.service;

import com.arogut.homex.edge.config.properties.ContractProperties;
import com.arogut.homex.edge.model.Measurement;
import com.arogut.homex.edge.model.MeasurementType;
import com.arogut.homex.edge.model.NumberMeasurement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class MeasurementCollectorServiceTest {

    private ContractProperties contractProperties = new ContractProperties();

    @Test
    void shouldReturnListOfGeneratedMeasurements() {
        NumberMeasurement temp = NumberMeasurement.builder()
                .name("temperature")
                .min(10)
                .max(15)
                .build();
        NumberMeasurement hum = NumberMeasurement.builder()
                .name("humidity")
                .min(0)
                .max(100)
                .build();
        contractProperties.setMeasurements(Map.of(
                MeasurementType.DOUBLE, List.of(temp),
                MeasurementType.INT, List.of(hum)
        ));
        MeasurementCollectorService collectorService = new MeasurementCollectorService(contractProperties);

        List<Measurement> measurements = collectorService.getMeasurement();

        Assertions.assertThat(measurements).extracting("name", "min", "max")
                .containsExactlyInAnyOrder(
                        Assertions.tuple(temp.getName(), temp.getMin(), temp.getMax()),
                        Assertions.tuple(hum.getName(), hum.getMin(), hum.getMax())
                );
        Assertions.assertThat(measurements).extracting("value").doesNotContainNull();
    }
}

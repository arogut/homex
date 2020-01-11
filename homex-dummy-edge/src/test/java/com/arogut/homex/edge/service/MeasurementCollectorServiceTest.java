package com.arogut.homex.edge.service;

import com.arogut.homex.edge.config.properties.ContractProperties;
import com.arogut.homex.edge.model.Measurement;
import com.arogut.homex.edge.model.MeasurementType;
import com.arogut.homex.edge.model.NumberMeasurement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MeasurementCollectorServiceTest {

    @Mock
    private ContractProperties contractProperties;

    @InjectMocks
    private MeasurementCollectorService collectorService;

    @Test
    void shouldReturnListOfGeneratedMeasurements() {
        Mockito.when(contractProperties.getMeasurements()).thenReturn(Map.of(
                MeasurementType.DOUBLE, List.of(NumberMeasurement.builder()
                        .name("temperature")
                        .min(10)
                        .max(15)
                        .build()),
                MeasurementType.INT, List.of(NumberMeasurement.builder()
                        .name("humidity")
                        .min(0)
                        .max(100)
                        .build())
        ));

        List<Measurement> measurements = collectorService.getMeasurement();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, measurements.size()),
                () -> Assertions.assertNotNull(measurements.get(0).getValue()),
                () -> Assertions.assertNotNull(measurements.get(1).getValue())
        );
    }
}

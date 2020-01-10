package com.arogut.homex.service;

import com.arogut.homex.model.DeviceMessage;
import com.arogut.homex.model.Measurement;
import org.assertj.core.api.Assertions;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;


@ExtendWith(MockitoExtension.class)
class DeviceMessageServiceTest {

    @Mock
    private InfluxDB influxDB;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceMessageService deviceMessageServiceService;

    @Test
    void shouldAcceptDeviceMessage() {
        DeviceMessage msg = DeviceMessage.builder()
                .deviceId("dummy")
                .measuredTime(Instant.now().toEpochMilli())
                .receivedTime(Instant.now().toEpochMilli())
                .data(List.of(Measurement.builder()
                        .name("temp")
                        .value("25").build()
                ))
                .build();

        deviceMessageServiceService.handle(msg);

        ArgumentCaptor<BatchPoints> pointsCaptor = ArgumentCaptor.forClass(BatchPoints.class);
        Mockito.verify(influxDB).write(pointsCaptor.capture());
        Assertions.assertThat(pointsCaptor.getValue()).isEqualTo(
                BatchPoints.builder()
                        .tag("deviceId", "dummy")
                        .point(
                                Point.measurement("measurement")
                                        .time(msg.getMeasuredTime(), TimeUnit.MILLISECONDS)
                                        .addField("temp", "25")
                                        .build()
                        )
                        .build()
        );
    }
}


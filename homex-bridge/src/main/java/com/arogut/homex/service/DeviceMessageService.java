package com.arogut.homex.service;

import com.arogut.homex.model.DeviceMessage;
import com.arogut.homex.model.Measurement;
import lombok.RequiredArgsConstructor;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DeviceMessageService {

    private final InfluxDB influxDB;
    private final DeviceService deviceService;

    public Mono<Boolean> handle(DeviceMessage deviceMessage) {
        return deviceService.existsById(deviceMessage.getDeviceId())
                .flatMap(b -> Mono.just(persist(deviceMessage.getDeviceId(), deviceMessage.getData(), deviceMessage.getMeasuredTime())))
                .switchIfEmpty(Mono.empty());
    }

    private boolean persist(String deviceId, List<Measurement> measurements, long measuredTime) {
        BatchPoints.Builder batchPoints = BatchPoints.builder().tag("deviceId", deviceId);
        measurements.forEach(m -> batchPoints.point(Point.measurement("measurement")
                .addField(m.getName(), m.getValue())
                .time(measuredTime, TimeUnit.MILLISECONDS)
                .build()));

        influxDB.write(batchPoints.build());
        return true;
    }

}

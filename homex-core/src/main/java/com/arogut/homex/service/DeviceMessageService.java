package com.arogut.homex.service;

import com.arogut.homex.model.DeviceMessage;
import com.arogut.homex.model.Measurement;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DeviceMessageService {

    private final InfluxDB influxDB;

    public DeviceMessageService(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    public void handle(DeviceMessage deviceMessage) {
        persist(deviceMessage.getData(), deviceMessage.getMeasuredTime());
    }

    private void persist(List<Measurement> measurements, long measuredTime) {
        Point.Builder pointBuilder = Point.measurement("measurement")
                .time(measuredTime, TimeUnit.MILLISECONDS)
                .fields(measurements.stream().collect(Collectors.toMap(Measurement::getName, Measurement::getValue)));
        influxDB.write(pointBuilder.build());
    }

}

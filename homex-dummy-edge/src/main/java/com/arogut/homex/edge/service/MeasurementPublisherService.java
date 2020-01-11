package com.arogut.homex.edge.service;

import com.arogut.homex.edge.client.BridgeClient;
import com.arogut.homex.edge.config.properties.EdgeProperties;
import com.arogut.homex.edge.model.DeviceMessage;
import com.arogut.homex.edge.model.Measurement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementPublisherService {

    private final EdgeProperties edgeProperties;
    private final MeasurementCollectorService collectorService;
    private final BridgeClient bridgeClient;

    @PostConstruct
    public void init() {
        subscribeToMeasurementsFlow(Schedulers.newSingle("measurement-sender"));
    }

    public void subscribeToMeasurementsFlow(Scheduler scheduler) {
        measurementsFlow()
                .subscribeOn(scheduler);
    }

    private Flux<List<Measurement>> measurementsFlow() {
        return Flux.interval(Duration.ofMillis(edgeProperties.getPublishDelay()), Duration.ofMillis(edgeProperties.getPublishPeriod()))
                .map(l -> collectorService.getMeasurement())
                .doOnNext(measurements -> {
                    DeviceMessage dm = DeviceMessage.builder()
                            .deviceId(edgeProperties.getDeviceId())
                            .measuredTime(System.currentTimeMillis())
                            .data(measurements)
                            .build();
                    bridgeClient.sendMessage(dm);
                })
                .onErrorContinue((e,o) -> log.warn("Unable to send measurements: {}", e.getMessage()));
    }
}

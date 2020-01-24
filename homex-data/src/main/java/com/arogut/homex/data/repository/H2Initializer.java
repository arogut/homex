package com.arogut.homex.data.repository;

import com.arogut.homex.data.model.Device;
import com.arogut.homex.data.model.DeviceType;
import com.arogut.homex.data.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Profile("dev")
@Component
@RequiredArgsConstructor
@Slf4j
public class H2Initializer {

    private final DeviceService deviceService;

    @PostConstruct
    public void initializeDb() {
        Device device = Device.builder()
                .id("2e68220f-6fbb-446f-b2be-4c8264f957ec")
                .name("dummy")
                .isConnected(true)
                .deviceType(DeviceType.SOURCE)
                .host("localhost")
                .port(999)
                .build();

        deviceService.add(device)
                .doOnNext(s -> log.info("[DEV profile] Added dummy device [{}] to DB",s ))
                .subscribe();
    }
}

package com.arogut.homex.gateway.service;

import com.arogut.homex.gateway.client.DataClient;
import com.arogut.homex.gateway.model.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final DataClient dataClient;

    public Mono<Device> register(Device device) {
        return dataClient.register(device);
    }
}

package com.arogut.homex.gateway.service;

import com.arogut.homex.gateway.client.BridgeClient;
import com.arogut.homex.gateway.model.Device;
import com.arogut.homex.gateway.model.DeviceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private BridgeClient bridgeClient;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void shouldProperlyCallBridgeToAddDevice() {
        Device device = Device.builder()
                .id("dummy")
                .name("dummy")
                .isConnected(true)
                .deviceType(DeviceType.SOURCE)
                .host("localhost")
                .port(999)
                .build();

        Mockito.when(bridgeClient.register(Mockito.any(Device.class)))
                .thenReturn(Mono.just(device));

        Assertions.assertThat(registrationService.register(device).block()).isEqualTo(device);


    }
}

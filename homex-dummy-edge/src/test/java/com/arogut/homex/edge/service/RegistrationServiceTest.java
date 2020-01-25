package com.arogut.homex.edge.service;


import com.arogut.homex.edge.client.AuthorizedGatewayClient;
import com.arogut.homex.edge.client.GatewayClient;
import com.arogut.homex.edge.config.properties.DeviceProperties;
import com.arogut.homex.edge.model.DeviceType;
import com.arogut.homex.edge.model.RegistrationDetails;
import com.arogut.homex.edge.model.RegistrationResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.TemporalUnitWithinOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private GatewayClient gatewayClient;
    @Mock
    private AuthorizedGatewayClient authorizedGatewayClient;

    private RegistrationDetails registrationDetails;
    private DeviceProperties deviceProperties;
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        registrationDetails = new RegistrationDetails();
        deviceProperties = buildDeviceProperties();
        registrationService = new RegistrationService(registrationDetails, gatewayClient, authorizedGatewayClient, deviceProperties);
    }

    @Test
    void shouldSuccessfullyRegister() {
        RegistrationResponse registrationResponse = buildRegistrationResponse();
        Mockito.when(gatewayClient.register(deviceProperties)).thenReturn(Mono.just(registrationResponse));

        StepVerifier.withVirtualTime(() -> registrationService.register())
                .expectSubscription()
                .assertNext(measurements -> {
                    Mockito.verify(gatewayClient, Mockito.times(1)).register(deviceProperties);
                    assertAuth();
                })
                .expectComplete()
                .verify();
    }

    @Test
    void shouldSuccessfullyScheduleTokenRefresh() {
        RegistrationResponse registrationResponse = buildRegistrationResponse();
        Mockito.when(authorizedGatewayClient.refresh()).thenReturn(Mono.just(registrationResponse));

        StepVerifier.withVirtualTime(() -> registrationService.scheduledRefresh(Duration.ofMillis(800)))
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(800))
                .assertNext(measurements -> {
                    Mockito.verify(authorizedGatewayClient, Mockito.times(1)).refresh();
                    assertAuth();
                })
                .expectNoEvent(Duration.ofMillis(800))
                .assertNext(measurements -> {
                    Mockito.verify(authorizedGatewayClient, Mockito.times(2)).refresh();
                    assertAuth();
                })
                .thenCancel()
                .verify();
    }

    private void assertAuth() {
        Assertions.assertThat(deviceProperties.getId()).isEqualTo("dummy-device");
        Assertions.assertThat(registrationDetails.isAuthorized()).isTrue();
        Assertions.assertThat(registrationDetails.getToken()).isEqualTo("real-token");
        Assertions.assertThat(registrationDetails.getExpiration()).isEqualTo(1000);
        Assertions.assertThat(registrationDetails.getLastTokenUpdate())
                .isCloseTo(LocalDateTime.now(), new TemporalUnitWithinOffset(300, ChronoUnit.MILLIS));
    }

    private DeviceProperties buildDeviceProperties() {
        return DeviceProperties.builder()
                .name("dummy-device-name")
                .host("local")
                .port(8909)
                .deviceType(DeviceType.SOURCE)
                .isConnected(true)
                .build();
    }

    private RegistrationResponse buildRegistrationResponse() {
        return RegistrationResponse.builder()
                .deviceId("dummy-device")
                .expiration(1000L)
                .token("real-token")
                .build();
    }
}

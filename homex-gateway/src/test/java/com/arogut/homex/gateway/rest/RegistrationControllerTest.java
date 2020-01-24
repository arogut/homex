package com.arogut.homex.gateway.rest;

import com.arogut.homex.gateway.JwtUtil;
import com.arogut.homex.gateway.model.AuthType;
import com.arogut.homex.gateway.model.Device;
import com.arogut.homex.gateway.model.DeviceType;
import com.arogut.homex.gateway.model.RegistrationResponse;
import com.arogut.homex.gateway.service.RegistrationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class RegistrationControllerTest {

    @MockBean
    private RegistrationService registrationService;

    @Autowired
    private WebTestClient webClient;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void shouldRegisterDeviceAndReturn200OK() {
        Device device = Device.builder()
                .id("dummy")
                .name("dummy")
                .isConnected(true)
                .deviceType(DeviceType.SOURCE)
                .host("localhost")
                .port(999)
                .build();

        Mockito.when(registrationService.register(Mockito.any(Device.class))).thenReturn(Mono.just(device));

        webClient.post()
                .uri("/devices/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(device), Device.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RegistrationResponse.class)
                .value(registrationResponse -> {
                    Assertions.assertThat(registrationResponse.getDeviceId()).isEqualTo("dummy");
                    Assertions.assertThat(registrationResponse.getToken()).isNotEmpty();
                });
    }

    @Test
    @WithMockUser
    void shouldNotAcceptDeviceAndReturn400() {
        Device device = Device.builder()
                .isConnected(true)
                .deviceType(DeviceType.SOURCE)
                .host("localhost")
                .port(999)
                .build();

        webClient.post()
                .uri("/devices/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(device), Device.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void shouldRefreshTokenForDevice() {
        String token = jwtUtil.generateToken("dummy", Map.of("role", AuthType.DEVICE));

        webClient.get()
                .uri("/devices/auth/refresh")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RegistrationResponse.class)
                .value(registrationResponse -> {
                    Assertions.assertThat(registrationResponse.getDeviceId()).isEqualTo("dummy");
                    Assertions.assertThat(registrationResponse.getToken()).isNotEmpty();
                });
    }

    @Test
    void refreshTokenEndpointShouldBeSecured() {
        webClient.get()
                .uri("/devices/auth/refresh")
                .exchange()
                .expectStatus().isUnauthorized();
    }
}

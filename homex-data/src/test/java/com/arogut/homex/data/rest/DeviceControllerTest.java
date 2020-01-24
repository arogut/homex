package com.arogut.homex.data.rest;

import com.arogut.homex.data.auth.JwtUtil;
import com.arogut.homex.data.auth.AuthType;
import com.arogut.homex.data.model.Device;
import com.arogut.homex.data.model.DeviceType;
import com.arogut.homex.data.service.DeviceService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class DeviceControllerTest {

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private WebTestClient webClient;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void shouldReturnDeviceAnd200OK() {
        Device dev = Device.builder().id("test").build();
        String token = jwtUtil.generateToken(dev.getId(), Map.of("role", AuthType.INTERNAL));

        Mockito.when(deviceService.getById("test")).thenReturn(Mono.just(dev));
        Mockito.when(deviceService.existsById("test")).thenReturn(Mono.just(true));

        webClient.get()
                .uri("/devices/test")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldReturn401WhenTokenCorrupted() {
        Device dev = Device.builder().id("test").build();
        String token = jwtUtil.generateToken(dev.getId(), Map.of("role", AuthType.INTERNAL));

        Mockito.when(deviceService.getById("test")).thenReturn(Mono.just(dev));
        Mockito.when(deviceService.existsById("test")).thenReturn(Mono.just(true));

        Mockito.when(jwtUtil.validateToken(token)).thenThrow(RuntimeException.class);

        webClient.get()
                .uri("/devices/test")
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    @WithMockUser
    void shouldReturn404NotFoundWhenNotExists() {
        Mockito.when(deviceService.getById("test12")).thenReturn(Mono.empty());

        webClient.get()
                .uri("/devices/test12")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @WithMockUser
    void shouldReturnDevicesAnd200OK() {
        Mockito.when(deviceService.getAll()).thenReturn(Flux.empty());

        webClient.get()
                .uri("/devices")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @WithMockUser
    void shouldAcceptDeviceAndReturn200OK() {
        Device device = Device.builder()
                .id("dummy")
                .name("dummy")
                .isConnected(true)
                .deviceType(DeviceType.SOURCE)
                .host("localhost")
                .port(999)
                .build();

        Mockito.when(deviceService.add(Mockito.any(Device.class))).thenReturn(Mono.just(device));

        webClient.post()
                .uri("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(device), Device.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Device.class)
                .value(d -> Assertions.assertThat(d).isEqualTo(device));
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
                .uri("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(device), Device.class)
                .exchange()
                .expectStatus().isBadRequest();
    }
}

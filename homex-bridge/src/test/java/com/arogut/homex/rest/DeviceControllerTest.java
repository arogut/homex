package com.arogut.homex.rest;

import com.arogut.homex.config.BridgeSecurityConfig;
import com.arogut.homex.model.Device;
import com.arogut.homex.model.DeviceType;
import com.arogut.homex.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DeviceController.class, BridgeSecurityConfig.class})
@WebFluxTest
class DeviceControllerTest {

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser
    void shouldReturnDeviceAnd200OK() {
        Device dev = Device.builder().id("test").build();

        Mockito.when(deviceService.getById("test")).thenReturn(Mono.just(dev));

        webClient.get()
                .uri("/devices/test")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @WithMockUser
    void shouldReturn404NotFoundWhenNotExisits() {
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
                .isConnected(true)
                .deviceType(DeviceType.SOURCE)
                .host("localhost")
                .port(999)
                .build();

        Mockito.when(deviceService.add(Mockito.any(Device.class))).thenReturn(Mono.just(device.getId()));

        webClient.post()
                .uri("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(device), Device.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Location", "/devices/dummy")
                .expectBody().isEmpty();
    }
}

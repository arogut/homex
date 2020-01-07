package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DeviceController.class})
@WebFluxTest
class DeviceControllerTest {

    private static final String USER = "admin";

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser
    void shouldReturnDeviceAnd200OK() {
        Device dev = Device.builder().id("test").build();

        when(deviceService.getById("test")).thenReturn(Mono.just(dev));

        webClient.get()
                .uri("/devices/test")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @WithMockUser
    void shouldReturn404NotFoundWhenNotExisits() {
        when(deviceService.getById("test12")).thenReturn(Mono.empty());

        webClient.get()
                .uri("/devices/test12")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @WithMockUser
    void shouldReturnDevicesAnd200OK() {
        when(deviceService.getAll()).thenReturn(Flux.empty());

        webClient.get()
                .uri("/devices")
                .exchange()
                .expectStatus().isOk();
    }
}

package com.arogut.homex.edge.client;

import com.arogut.homex.edge.config.properties.DeviceProperties;
import com.arogut.homex.edge.model.RegistrationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@ReactiveFeignClient(name = "GatewayClient", url="${gateway.url}")
public interface GatewayClient {

    @PostMapping("/devices/register")
    Mono<RegistrationResponse> register(@Valid @RequestBody DeviceProperties device);
}

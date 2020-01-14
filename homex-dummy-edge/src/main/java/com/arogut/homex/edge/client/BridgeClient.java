package com.arogut.homex.edge.client;

import com.arogut.homex.edge.model.DeviceMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@ReactiveFeignClient(name = "BridgeClient", url="${bridge.url}")
public interface BridgeClient {

    @PostMapping("/devices/message")
    Mono<String> sendMessage(@Valid @RequestBody DeviceMessage message);
}

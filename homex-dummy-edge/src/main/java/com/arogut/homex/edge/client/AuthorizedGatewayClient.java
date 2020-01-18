package com.arogut.homex.edge.client;

import com.arogut.homex.edge.model.DeviceMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@ReactiveFeignClient(name = "AuthorizedGatewayClient", url="${gateway.url}", configuration = FeignClientJwtInterceptor.class)
public interface AuthorizedGatewayClient {

    @PostMapping("/devices/message")
    Mono<String> sendMessage(@Valid @RequestBody DeviceMessage message);
}

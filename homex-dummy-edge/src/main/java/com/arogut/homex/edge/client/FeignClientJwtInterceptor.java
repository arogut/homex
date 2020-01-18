package com.arogut.homex.edge.client;

import com.arogut.homex.edge.config.properties.ContractProperties;
import lombok.RequiredArgsConstructor;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
public class FeignClientJwtInterceptor implements ReactiveHttpRequestInterceptor {

    private final ContractProperties contract;
    private final GatewayClient gatewayClient;
    private String token;

    @PostConstruct
    public void setUp() {
        updateToken();
    }

    @Override
    public ReactiveHttpRequest apply(ReactiveHttpRequest reactiveHttpRequest) {
        reactiveHttpRequest.headers().put("Authorization", List.of("Bearer " + token));
        return reactiveHttpRequest;
    }

    private void updateToken() {
        Flux.interval(Duration.ofMillis(500), Duration.ofMillis(2000))
                .flatMap(i -> gatewayClient.register(contract.getDevice()))
                .doOnNext(response -> {
                    this.token = response.getToken();
                    contract.getDevice().setId(response.getDeviceId());
                })
                .subscribe();
    }
}

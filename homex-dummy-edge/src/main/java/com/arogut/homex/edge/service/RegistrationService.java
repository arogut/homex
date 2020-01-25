package com.arogut.homex.edge.service;

import com.arogut.homex.edge.client.AuthorizedGatewayClient;
import com.arogut.homex.edge.client.GatewayClient;
import com.arogut.homex.edge.config.properties.DeviceProperties;
import com.arogut.homex.edge.model.RegistrationDetails;
import com.arogut.homex.edge.model.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final RegistrationDetails registrationDetails;
    private final GatewayClient gatewayClient;
    private final AuthorizedGatewayClient authorizedGatewayClient;
    private final DeviceProperties deviceProperties;

    @PostConstruct
    public void setUp() {
        register().flatMap(response -> scheduledRefresh(Duration.ofMillis(response.getExpiration() * 8 / 10))).subscribe();
    }

    public Flux<RegistrationResponse> register() {
        return Flux.from(gatewayClient.register(deviceProperties))
                .retryBackoff(100, Duration.ofMillis(1000), Duration.ofMillis(10000))
                .doOnNext(response -> {
                    updateRegistrationDetails(response);
                    log.info("Registered with id: {}", response.getDeviceId());
                });
    }

    public Flux<RegistrationResponse> scheduledRefresh(Duration duration) {
        return Flux.interval(duration, duration)
                .flatMap(i -> authorizedGatewayClient.refresh())
                .doOnNext(this::updateRegistrationDetails);
    }

    private void updateRegistrationDetails(RegistrationResponse response) {
        deviceProperties.setId(response.getDeviceId());
        registrationDetails.setAuthorized(true);
        registrationDetails.setToken(response.getToken());
        registrationDetails.setExpiration(response.getExpiration());
        registrationDetails.setLastTokenUpdate(LocalDateTime.now());
    }
}

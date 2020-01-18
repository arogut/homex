package com.arogut.homex.gateway.rest;

import com.arogut.homex.gateway.JwtUtil;
import com.arogut.homex.gateway.model.AuthType;
import com.arogut.homex.gateway.model.Device;
import com.arogut.homex.gateway.model.RegistrationResponse;
import com.arogut.homex.gateway.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/devices/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public Mono<ResponseEntity<RegistrationResponse>> register(@Valid @RequestBody Device device) {
        return registrationService.register(device)
                .map(d -> RegistrationResponse.builder()
                        .deviceId(d.getId())
                        .token(jwtUtil.generateToken(d.getId(), Map.of("role", AuthType.DEVICE)))
                        .build())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}

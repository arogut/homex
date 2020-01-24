package com.arogut.homex.gateway.rest;

import com.arogut.homex.gateway.JwtUtil;
import com.arogut.homex.gateway.model.AuthType;
import com.arogut.homex.gateway.model.Device;
import com.arogut.homex.gateway.model.RegistrationResponse;
import com.arogut.homex.gateway.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/devices/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private long expiration;

    @PostMapping
    public Mono<ResponseEntity<RegistrationResponse>> register(@Valid @RequestBody Device device) {
        return registrationService.register(device)
                .flatMap(d -> toResponse(d::getId))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/refresh")
    public Mono<ResponseEntity<RegistrationResponse>> refresh(@RequestHeader(value = "Authorization", required = false) String token) {
        return Mono.justOrEmpty(token)
                .map(t -> t.substring(7))
                .filter(t -> !jwtUtil.isTokenExpired(t))
                .map(jwtUtil::getAllClaimsFromToken)
                .flatMap(claims -> toResponse(claims::getSubject))
                .defaultIfEmpty(ResponseEntity.status(401).build());
    }

    private Mono<ResponseEntity<RegistrationResponse>> toResponse(Supplier<String> deviceIdSupplier) {
        return Mono.just(RegistrationResponse.builder()
                .deviceId(deviceIdSupplier.get())
                .token(jwtUtil.generateToken(deviceIdSupplier.get(), Map.of("role", AuthType.DEVICE)))
                .expiration(expiration)
                .build())
                .map(ResponseEntity::ok);
    }
}

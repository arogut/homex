package com.arogut.homex.bridge.auth;

import com.arogut.homex.bridge.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final DeviceService deviceService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        String deviceId;
        try {
            deviceId = jwtUtil.getUsernameFromToken(authToken);
        } catch (Exception e) {
            return Mono.empty();
        }
        return Mono.justOrEmpty(deviceId)
                .flatMap(deviceService::existsById)
                .filter(Boolean::booleanValue)
                .map(b -> jwtUtil.validateToken(authToken))
                .filter(Boolean::booleanValue)
                .flatMap(b -> {
                    Authentication auth = new UsernamePasswordAuthenticationToken(deviceId, null,
                            List.of(new SimpleGrantedAuthority("device")));
                    return Mono.just(auth);
                })
                .switchIfEmpty(Mono.empty());
    }
}

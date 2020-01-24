package com.arogut.homex.data.auth;

import com.arogut.homex.data.service.DeviceService;
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
        return Mono.just(authentication.getCredentials().toString())
                .filterWhen(token -> Mono.just(jwtUtil.validateToken(token)))
                .map(jwtUtil::getSubjectAndType)
                .onErrorResume(e -> Mono.empty())
                .flatMap(pair -> {
                    if(pair.getAuthType().equals(AuthType.INTERNAL)) {
                        return internalAuth(pair.getSubject());
                    } else if (pair.getAuthType().equals(AuthType.DEVICE)) {
                        return deviceAuth(pair.getSubject());
                    } else {
                        throw new IllegalArgumentException("Wrong auth type");
                    }
                })
                .switchIfEmpty(Mono.empty());
    }

    private Mono<Authentication> internalAuth(String subject) {
        return Mono.just(new UsernamePasswordAuthenticationToken(subject, null,
                List.of(new SimpleGrantedAuthority(AuthType.INTERNAL.toString()))));
    }

    private Mono<Authentication> deviceAuth(String subject) {
        return deviceService.existsById(subject)
                .filter(Boolean::booleanValue)
                .map(b -> new UsernamePasswordAuthenticationToken(subject, null,
                        List.of(new SimpleGrantedAuthority(AuthType.DEVICE.toString()))));
    }
}

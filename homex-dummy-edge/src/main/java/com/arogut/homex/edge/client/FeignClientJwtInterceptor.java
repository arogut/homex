package com.arogut.homex.edge.client;

import com.arogut.homex.edge.model.RegistrationDetails;
import lombok.RequiredArgsConstructor;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;

import java.util.List;

@RequiredArgsConstructor
public class FeignClientJwtInterceptor implements ReactiveHttpRequestInterceptor {

    private final RegistrationDetails registrationDetails;

    @Override
    public ReactiveHttpRequest apply(ReactiveHttpRequest reactiveHttpRequest) {
        reactiveHttpRequest.headers().put("Authorization", List.of("Bearer " + registrationDetails.getToken()));
        return reactiveHttpRequest;
    }
}

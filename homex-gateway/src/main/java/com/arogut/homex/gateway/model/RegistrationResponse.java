package com.arogut.homex.gateway.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegistrationResponse {

    private String deviceId;

    private String token;
}

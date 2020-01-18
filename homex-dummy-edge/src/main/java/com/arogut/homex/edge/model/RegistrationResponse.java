package com.arogut.homex.edge.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegistrationResponse {

    private String deviceId;

    private String token;
}

package com.arogut.shas.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
public class RegisterMessage {
    private String deviceName;
    private Instant timestamp;
    private String host;
    private int port;
    private DeviceType deviceType;
    private Map<String, Object> fields;
}

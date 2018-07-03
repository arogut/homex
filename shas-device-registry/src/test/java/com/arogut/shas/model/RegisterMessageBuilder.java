package com.arogut.shas.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RegisterMessageBuilder {
    private RegisterMessage message;

    public RegisterMessageBuilder() {
        message = new RegisterMessage();
        message.setFields(new HashMap<>());
    }

    public RegisterMessageBuilder withDeviceName(String deviceName) {
        message.setDeviceName(deviceName);
        return this;
    }

    public RegisterMessageBuilder withHost(String host) {
        message.setHost(host);
        return this;
    }

    public RegisterMessageBuilder withPort(int port) {
        message.setPort(port);
        return this;
    }

    public RegisterMessageBuilder withDeviceType(DeviceType deviceType) {
        message.setDeviceType(deviceType);
        return this;
    }

    public RegisterMessageBuilder withFields(Map<String, Object> fields) {
        message.setFields(fields);
        return this;
    }

    public RegisterMessage build() {
        return message;
    }
}

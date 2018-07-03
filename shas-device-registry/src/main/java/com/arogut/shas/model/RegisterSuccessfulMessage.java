package com.arogut.shas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RegisterSuccessfulMessage {
    REGISTRATION_COMPLETED("Registration completed");

    private String message;
    private String deviceId;

    RegisterSuccessfulMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

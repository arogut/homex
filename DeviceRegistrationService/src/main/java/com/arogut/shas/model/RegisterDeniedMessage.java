package com.arogut.shas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RegisterDeniedMessage {
    ALREADY_REGISTERED("Already registered"), REGISTER_TURNED_OFF("Register turned off");

    private String message;

    RegisterDeniedMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

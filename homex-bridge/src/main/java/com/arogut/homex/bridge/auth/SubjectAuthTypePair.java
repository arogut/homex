package com.arogut.homex.bridge.auth;

import lombok.Value;

@Value
public class SubjectAuthTypePair {

    private String subject;
    private AuthType authType;
}

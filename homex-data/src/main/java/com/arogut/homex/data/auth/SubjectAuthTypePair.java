package com.arogut.homex.data.auth;

import lombok.Value;

@Value
public class SubjectAuthTypePair {

    private String subject;
    private AuthType authType;
}

package com.arogut.homex.bridge.config.auth;

import com.arogut.homex.bridge.model.AuthType;
import lombok.Value;

@Value
public class SubjectAuthTypePair {

    private String subject;
    private AuthType authType;
}

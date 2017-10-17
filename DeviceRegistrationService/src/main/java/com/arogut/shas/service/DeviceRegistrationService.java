package com.arogut.shas.service;

import com.arogut.shas.model.RegisterMessage;

import java.util.Optional;

public interface DeviceRegistrationService {

    Optional<String> register(RegisterMessage message);
}

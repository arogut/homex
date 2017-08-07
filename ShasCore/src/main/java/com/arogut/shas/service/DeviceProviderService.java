package com.arogut.shas.service;

import com.arogut.shas.model.jpa.Device;

import java.util.Optional;

public interface DeviceProviderService {

    Iterable<Device> getAll();

    Optional<Device> getById(String id);
}

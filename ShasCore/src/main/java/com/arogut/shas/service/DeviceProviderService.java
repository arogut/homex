package com.arogut.shas.service;

import com.arogut.shas.model.Device;

import java.util.Optional;

public interface DeviceProviderService {

    Iterable<Device> getAll();

    Optional<Device> getById(String id);
}

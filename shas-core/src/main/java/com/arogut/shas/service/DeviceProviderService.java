package com.arogut.shas.service;

import com.arogut.shas.model.Device;
import com.arogut.shas.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceProviderService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceProviderService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Iterable<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(String id) {
        return deviceRepository.findOneById(id);
    }
}

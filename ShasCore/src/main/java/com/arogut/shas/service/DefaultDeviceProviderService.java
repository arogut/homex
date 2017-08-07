package com.arogut.shas.service;

import com.arogut.shas.model.jpa.Device;
import com.arogut.shas.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDeviceProviderService implements DeviceProviderService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DefaultDeviceProviderService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Iterable<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> getById(String id) {
        return deviceRepository.findOneById(id);
    }
}

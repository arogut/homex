package com.arogut.homex.service;

import com.arogut.homex.model.Device;
import com.arogut.homex.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Iterable<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(String id) {
        return deviceRepository.findOneById(id);
    }

    public String add(Device device) {
        return deviceRepository.save(device).getId();
    }
}

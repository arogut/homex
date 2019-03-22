package com.arogut.homex.service;

import com.arogut.homex.model.Device;
import com.arogut.homex.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeviceRegistrationService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceRegistrationService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public Optional<String> register(Device device) {
        return deviceRepository.findOneByHostAndDeviceType(device.getHost(), device.getDeviceType())
                .map(x -> Optional.<String>empty())
                .orElse(persistDeviceMetadata(device));
    }

    private Optional<String> persistDeviceMetadata(Device device) {
        Device d = deviceRepository.save(device);

        return Optional.ofNullable(d.getId());
    }
}

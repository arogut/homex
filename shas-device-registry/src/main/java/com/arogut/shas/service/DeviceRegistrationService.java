package com.arogut.shas.service;

import com.arogut.shas.model.Device;
import com.arogut.shas.model.RegisterMessage;
import com.arogut.shas.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceRegistrationService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceRegistrationService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public Optional<String> register(RegisterMessage message) {
        return deviceRepository.findOneByHostAndDeviceType(message.getHost(),message.getDeviceType())
                .map(x -> Optional.<String>empty())
                .orElse(persistDeviceMetadata(mapToDevice(message)));
    }

    private Optional<String> persistDeviceMetadata(Device device) {
        Device d = deviceRepository.save(device);

        return Optional.ofNullable(d.getId());
    }

    private Device mapToDevice(RegisterMessage message) {
        String uuid = UUID.randomUUID().toString();

        Device newDevice = new Device();
        newDevice.setId(uuid);
        newDevice.setName(message.getDeviceName());
        newDevice.setConnected(true);
        newDevice.setHost(message.getHost());

        return newDevice;
    }
}

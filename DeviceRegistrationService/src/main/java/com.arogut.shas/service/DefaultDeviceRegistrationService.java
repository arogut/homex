package com.arogut.shas.service;

import com.arogut.shas.model.DeviceType;
import com.arogut.shas.model.RegisterMessage;
import com.arogut.shas.model.jpa.SourceDevice;
import com.arogut.shas.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultDeviceRegistrationService implements DeviceRegistrationService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DefaultDeviceRegistrationService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Optional<String> register(RegisterMessage message) {
        if(message.getDeviceType().equals(DeviceType.SOURCE)) {
            return registerSourceDevice(message);
        }
        return Optional.empty();
    }

    private Optional<String> registerSourceDevice(RegisterMessage message) {
        String uuid = UUID.randomUUID().toString();

        SourceDevice newDevice = new SourceDevice();
        newDevice.setId(uuid);
        newDevice.setName(message.getDeviceName());
        newDevice.setConnected(true);
        newDevice.setLastConnection(message.getTimestamp());
        newDevice.setHost(message.getHost());

        deviceRepository.save(newDevice);

        return Optional.of(uuid);
    }
}

package com.arogut.homex.service;

import com.arogut.homex.model.DeviceMessage;
import com.arogut.homex.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementProviderService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementProviderService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Iterable<DeviceMessage> getAll() {
        return measurementRepository.findAll();
    }

    public Iterable<DeviceMessage> getAllByDeviceId(String deviceId) {
        return measurementRepository.getAllByDeviceId(deviceId);
    }

}

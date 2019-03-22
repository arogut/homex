package com.arogut.homex.service;

import com.arogut.homex.model.Measurement;
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

    public Iterable<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public Iterable<Measurement> getAllByDeviceId(String deviceId) {
        return measurementRepository.getAllByDeviceId(deviceId);
    }

}

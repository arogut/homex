package com.arogut.homex.data.service;

import com.arogut.homex.data.dao.DeviceEntity;
import com.arogut.homex.data.dao.DeviceRepository;
import com.arogut.homex.data.mapper.DeviceMapper;
import com.arogut.homex.data.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    private final DeviceMapper deviceMapper;

    public Flux<Device> getAll() {
        return Flux.fromIterable(deviceRepository.findAll())
                .map(deviceMapper::toDevice);
    }

    public Mono<Device> getById(String id) {
        return Mono.justOrEmpty(deviceRepository.findById(id))
                .map(deviceMapper::toDevice);
    }

    public Mono<Boolean> existsById(String id) {
        return Mono.just(deviceRepository.existsById(id));
    }

    public Mono<Device> add(Device device) {
        return Mono.just(deviceRepository.findByMacAddress(device.getMacAddress())
                .map(deviceMapper::toDevice)
                .orElseGet(() -> deviceMapper.toDevice(create(device))));
    }

    private DeviceEntity create(Device device) {
        return deviceRepository.save(deviceMapper.toEntity(device));
    }
}

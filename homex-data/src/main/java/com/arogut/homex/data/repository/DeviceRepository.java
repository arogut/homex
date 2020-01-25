package com.arogut.homex.data.repository;

import com.arogut.homex.data.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {

    Optional<Device> findByMacAddress(String macAddress);
}

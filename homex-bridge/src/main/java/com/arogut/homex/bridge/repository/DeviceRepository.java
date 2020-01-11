package com.arogut.homex.bridge.repository;

import com.arogut.homex.bridge.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {
}

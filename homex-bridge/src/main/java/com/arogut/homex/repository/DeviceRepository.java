package com.arogut.homex.repository;

import com.arogut.homex.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {
}

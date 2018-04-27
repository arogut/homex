package com.arogut.shas.model.jpa.repository;

import com.arogut.shas.model.DeviceType;
import com.arogut.shas.model.jpa.entity.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, String> {

    Optional<Device> findOneById(String id);

    Optional<Device> findOneByHostAndDeviceType(String host, DeviceType deviceType);
}

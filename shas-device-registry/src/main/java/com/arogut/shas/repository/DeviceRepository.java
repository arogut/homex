package com.arogut.shas.repository;

import com.arogut.shas.model.Device;
import com.arogut.shas.model.DeviceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {

    Optional<Device> findOneById(String id);

    Optional<Device> findOneByHostAndDeviceType(String host, DeviceType deviceType);
}

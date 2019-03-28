package com.arogut.homex.repository;

import com.arogut.homex.model.DeviceMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends CrudRepository<DeviceMessage, Long> {

    Iterable<DeviceMessage> getAllByDeviceId(String deviceId);
}

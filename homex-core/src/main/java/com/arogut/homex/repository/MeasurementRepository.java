package com.arogut.homex.repository;

import com.arogut.homex.model.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

    Iterable<Measurement> getAllByDeviceId(String deviceId);
}

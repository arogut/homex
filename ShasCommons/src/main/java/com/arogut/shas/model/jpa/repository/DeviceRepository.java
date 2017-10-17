package com.arogut.shas.model.jpa.repository;

import com.arogut.shas.model.jpa.entity.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, String> {

    Optional<Device> findOneById(String id);
}

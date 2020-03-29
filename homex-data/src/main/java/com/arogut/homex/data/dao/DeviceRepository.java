package com.arogut.homex.data.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, String> {

    Optional<DeviceEntity> findByMacAddress(String macAddress);
}

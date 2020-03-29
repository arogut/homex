package com.arogut.homex.data.mapper;

import com.arogut.homex.data.dao.DeviceEntity;
import com.arogut.homex.data.model.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceEntity toEntity(Device device);

    Device toDevice(DeviceEntity entity);
}

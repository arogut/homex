package com.arogut.homex.data.mapper;

import com.arogut.homex.data.dao.DeviceEntity;
import com.arogut.homex.data.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mapping(ignore = true, target = "id")
    DeviceEntity toEntity(Device device);

    @Mapping(target = "measurements", ignore = true)
    @Mapping(target = "commands", ignore = true)
    Device toDevice(DeviceEntity entity);

    Device toDeviceWithChildren(DeviceEntity entity);
}

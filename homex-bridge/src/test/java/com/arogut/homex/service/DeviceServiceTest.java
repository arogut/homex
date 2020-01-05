package com.arogut.homex.service;

import com.arogut.homex.model.Device;
import com.arogut.homex.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void shouldReturnAllDevicesFromDB() {
        List<Device> devices = Arrays.asList(Device.builder().build(), Device.builder().build());
        when(deviceRepository.findAll()).thenReturn(devices);

        assertThat(deviceService.getAll().collectList().block()).containsExactlyElementsOf(devices);
    }

    @Test
    void shouldReturnSingleDeviceById() {
        Device device = Device.builder().id("1").build();
        when(deviceRepository.findOneById(anyString())).thenReturn(Optional.ofNullable(device));

        assertThat(deviceService.getById("1").blockOptional()).isPresent();
        assertThat(deviceService.getById("1").blockOptional()).contains(device);
    }
}

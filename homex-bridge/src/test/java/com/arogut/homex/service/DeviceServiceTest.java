package com.arogut.homex.service;

import com.arogut.homex.model.Device;
import com.arogut.homex.repository.DeviceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void shouldReturnAllDevicesFromDB() {
        List<Device> devices = Arrays.asList(Device.builder().build(), Device.builder().build());
        Mockito.when(deviceRepository.findAll()).thenReturn(devices);

        Assertions.assertThat(deviceService.getAll().collectList().block()).containsExactlyElementsOf(devices);
    }

    @Test
    void shouldReturnSingleDeviceById() {
        Device device = Device.builder().id("1").build();
        Mockito.when(deviceRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(device));

        Assertions.assertThat(deviceService.getById("1").blockOptional()).isPresent();
        Assertions.assertThat(deviceService.getById("1").blockOptional()).contains(device);
    }

    @Test
    void shouldSuccessfullyAddDevice() {
        Device device = Device.builder().id("1").build();
        Mockito.when(deviceRepository.save(device)).thenReturn(device);

        Assertions.assertThat(deviceService.add(device).block()).isEqualTo(device.getId());
    }

    @Test
    void shouldReturnTrueWhenDeviceExists() {
        Mockito.when(deviceRepository.existsById(Mockito.anyString())).thenReturn(true);

        Assertions.assertThat(deviceService.existsById("1").block()).isEqualTo(true);
    }
}

package com.arogut.homex.service;

import com.arogut.homex.model.Device;
import com.arogut.homex.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceRegistrationServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceRegistrationService deviceRegistrationService;

    @Test
    void shouldRegisterDevice() {
        Device sourceDevice = Device.builder()
                .id("test")
                .host("127.0.0.1")
                .isConnected(true)
                .build();
        when(deviceRepository.save(any())).thenReturn(sourceDevice);
        when(deviceRepository.findOneByHostAndDeviceType(anyString(), any()))
                .thenReturn(Optional.empty());

        Optional<String> id = deviceRegistrationService.register(sourceDevice);

        assertThat(id).isPresent();
    }

    @Test
    void shouldReturnRegisterDeviceWithSameHostAndType() {
        Device sourceDevice = Device.builder()
                .id("1")
                .build();
        when(deviceRepository.save(any())).thenReturn(sourceDevice);

        Optional<String> id = deviceRegistrationService.register(sourceDevice);

        assertThat(id).isPresent();
    }
}

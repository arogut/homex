package com.arogut.shas.service;

import com.arogut.shas.model.*;
import com.arogut.shas.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceRegistrationServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceRegistrationService deviceRegistrationService;

    @Test
    public void shouldRegisterDevice() {
        Instant lastConnection = Instant.now();
        Device sourceDevice = new DeviceBuilder<>(new Device())
                .withId("test")
                .withHost("127.0.0.1")
                .withIsConnected(true)
                .build();
        RegisterMessage message = new RegisterMessageBuilder()
                .withDeviceName("test")
                .withHost("127.0.0.1")
                .withDeviceType(DeviceType.SOURCE)
                .build();
        when(deviceRepository.save(Matchers.any())).thenReturn(sourceDevice);
        when(deviceRepository.findOneByHostAndDeviceType(Matchers.anyString(), Matchers.any()))
                .thenReturn(Optional.empty());

        Optional<String> id = deviceRegistrationService.register(message);

        assertThat(id).isPresent();
    }

    @Test
    public void shouldReturnRegisterDeviceWithSameHostAndType() {
        Device sourceDevice = new DeviceBuilder<>(new Device())
                .withId("1")
                .build();
        RegisterMessage message = new RegisterMessageBuilder()
                .build();
        when(deviceRepository.save(Matchers.<Device>any())).thenReturn(sourceDevice);

        Optional<String> id = deviceRegistrationService.register(message);

        assertThat(id).isPresent();
    }
}

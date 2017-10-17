package com.arogut.shas.service;

import com.arogut.shas.model.DeviceBuilder;
import com.arogut.shas.model.DeviceType;
import com.arogut.shas.model.RegisterMessage;
import com.arogut.shas.model.RegisterMessageBuilder;
import com.arogut.shas.model.jpa.entity.SourceDevice;
import com.arogut.shas.model.jpa.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDeviceRegistrationServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DefaultDeviceRegistrationService deviceRegistrationService;

    @Test
    public void shouldRegisterDevice() {
        Instant lastConnection = Instant.now();
        SourceDevice sourceDevice = new DeviceBuilder<>(new SourceDevice())
                .withId("test")
                .withHost("127.0.0.1")
                .withLastConnection(lastConnection)
                .withIsConnected(true)
                .build();
        RegisterMessage message = new RegisterMessageBuilder()
                .withDeviceName("test")
                .withHost("127.0.0.1")
                .withDeviceType(DeviceType.SOURCE)
                .build();
        when(deviceRepository.save(Matchers.<SourceDevice>any())).thenReturn(sourceDevice);

        Optional<String> id = deviceRegistrationService.register(message);

        assertThat(id).isPresent();
    }
}

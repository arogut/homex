package com.arogut.homex.data.repository;

import com.arogut.homex.data.model.Device;
import com.arogut.homex.data.model.DeviceType;
import com.arogut.homex.data.service.DeviceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class H2InitializerTest {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private H2Initializer h2Initializer;

    @Test
    void shouldCreateDummyDevice() {
        Mockito.when(deviceService.add(Mockito.any(Device.class))).thenAnswer(i -> Mono.just(i.getArgument(0)));

        h2Initializer.initializeDb();

        ArgumentCaptor<Device> deviceCaptor = ArgumentCaptor.forClass(Device.class);
        Mockito.verify(deviceService).add(deviceCaptor.capture());
        Device dbDevice = deviceCaptor.getValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals("dummy", dbDevice.getName()),
                () -> Assertions.assertTrue(dbDevice.isConnected()),
                () -> Assertions.assertEquals(DeviceType.SOURCE, dbDevice.getDeviceType()),
                () -> Assertions.assertEquals("localhost", dbDevice.getHost()),
                () -> Assertions.assertEquals(999, dbDevice.getPort())
        );
    }
}

package com.arogut.homex.bridge.repository;

import com.arogut.homex.bridge.model.Device;
import com.arogut.homex.bridge.model.DeviceType;
import com.arogut.homex.bridge.service.DeviceService;
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
        Mockito.when(deviceService.add(Mockito.any(Device.class))).thenReturn(Mono.just("id"));

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

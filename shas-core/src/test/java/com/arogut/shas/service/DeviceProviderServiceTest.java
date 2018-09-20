package com.arogut.shas.service;

import com.arogut.shas.model.Device;
import com.arogut.shas.model.DeviceBuilder;
import com.arogut.shas.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceProviderServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceProviderService deviceProviderService;

    @Test
    public void shouldReturnAllDevicesFromDB() {
        List<Device> devices = Arrays.asList(new Device(), new Device());
        when(deviceRepository.findAll()).thenReturn(devices);

        assertThat(deviceProviderService.getAll()).containsExactlyElementsOf(devices);
    }

    @Test
    public void shouldReturnSingleDeviceById() {
        Device device = new DeviceBuilder<>(new Device()).withId("1").build();
        when(deviceRepository.findOneById(anyString())).thenReturn(Optional.of(device));

        assertThat(deviceProviderService.getById("1")).isPresent();
        assertThat(deviceProviderService.getById("1")).contains(device);
    }
}

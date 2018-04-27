package com.arogut.shas.service;

import com.arogut.shas.model.DeviceBuilder;
import com.arogut.shas.model.jpa.entity.Device;
import com.arogut.shas.model.jpa.entity.SourceDevice;
import com.arogut.shas.model.jpa.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDeviceProviderServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DefaultDeviceProviderService deviceProviderService;

    @Test
    public void shouldReturnAllDevicesFromDB() {
        List<Device> devices = Arrays.asList(new SourceDevice(), new SourceDevice());
        when(deviceRepository.findAll()).thenReturn(devices);

        assertThat(deviceProviderService.getAll()).containsExactlyElementsOf(devices);
    }

    @Test
    public void shouldReturnSingleDeviceById() {
        SourceDevice device = new DeviceBuilder<>(new SourceDevice()).withId("1").build();
        when(deviceRepository.findOneById(Matchers.anyString())).thenReturn(Optional.of(device));

        assertThat(deviceProviderService.getById("1")).isPresent();
        assertThat(deviceProviderService.getById("1")).contains(device);
    }
}

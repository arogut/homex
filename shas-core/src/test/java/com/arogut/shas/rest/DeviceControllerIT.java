package com.arogut.shas.rest;

import com.arogut.shas.model.DeviceBuilder;
import com.arogut.shas.model.jpa.entity.Device;
import com.arogut.shas.model.jpa.entity.SourceDevice;
import com.arogut.shas.service.DefaultDeviceProviderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class DeviceControllerIT {

    @Mock
    private DefaultDeviceProviderService deviceProvider;

    @InjectMocks
    private DeviceController deviceController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    public void shouldReturnDeviceAnd200OK() throws Exception {
        Device dev = new DeviceBuilder<>(new SourceDevice()).withId("test").build();

        when(deviceProvider.getById("test")).thenReturn(Optional.ofNullable(dev));

        mockMvc.perform(get("/devices/test")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400NotFoundWhenNotExisits() throws Exception {
        when(deviceProvider.getById("test12")).thenReturn(Optional.empty());

        mockMvc.perform(get("/devices/test12")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnDevicesAnd200OK() throws Exception {
        Device dev = new DeviceBuilder<>(new SourceDevice()).withId("test").build();

        when(deviceProvider.getById("test")).thenReturn(Optional.ofNullable(dev));

        mockMvc.perform(get("/devices")).andExpect(status().isOk());
    }
}

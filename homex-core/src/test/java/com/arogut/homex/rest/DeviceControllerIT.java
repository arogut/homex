package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class DeviceControllerIT {

    @Mock
    private DeviceService deviceProvider;

    @InjectMocks
    private DeviceController deviceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    void shouldReturnDeviceAnd200OK() throws Exception {
        Device dev = Device.builder().id("test").build();

        when(deviceProvider.getById("test")).thenReturn(Optional.ofNullable(dev));

        mockMvc.perform(get("/devices/test")).andExpect(status().isOk());
    }

    @Test
    void shouldReturn400NotFoundWhenNotExisits() throws Exception {
        when(deviceProvider.getById("test12")).thenReturn(Optional.empty());

        mockMvc.perform(get("/devices/test12")).andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnDevicesAnd200OK() throws Exception {
        Device dev = Device.builder().id("test").build();

        when(deviceProvider.getById("test")).thenReturn(Optional.ofNullable(dev));

        mockMvc.perform(get("/devices")).andExpect(status().isOk());
    }
}

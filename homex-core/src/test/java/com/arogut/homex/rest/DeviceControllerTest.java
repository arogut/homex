package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
class DeviceControllerTest {

    private static final String USER = "admin";

    @MockBean
    private DeviceService deviceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDeviceAnd200OK() throws Exception {
        Device dev = Device.builder().id("test").build();

        when(deviceService.getById("test")).thenReturn(Optional.ofNullable(dev));

        mockMvc.perform(get("/device/test").with(user(USER))).andExpect(status().isOk());
    }

    @Test
    void shouldReturn400NotFoundWhenNotExisits() throws Exception {
        when(deviceService.getById("test12")).thenReturn(Optional.empty());

        mockMvc.perform(get("/device/test12").with(user(USER))).andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnDevicesAnd200OK() throws Exception {
        Device dev = Device.builder().id("test").build();

        when(deviceService.getById("test")).thenReturn(Optional.ofNullable(dev));

        mockMvc.perform(get("/device").with(user(USER))).andExpect(status().isOk());
    }
}

package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.model.DeviceType;
import com.arogut.homex.service.DeviceRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class RegistrationControllerIT {

    @Mock
    private DeviceRegistrationService deviceRegistrationService;

    @InjectMocks
    private RegistrationController registrationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void shouldRegisterDevice200OK() throws Exception {
        Device message = Device.builder().name("test").host("127.0.0.1").deviceType(DeviceType.SOURCE).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(message);

        when(deviceRegistrationService.register(any())).thenReturn(Optional.of("new-pretty-unique-id"));

        mockMvc.perform(post("/register").content(jsonString).header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }
}

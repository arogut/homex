package com.arogut.shas.rest;

import com.arogut.shas.model.DeviceType;
import com.arogut.shas.model.RegisterMessageBuilder;
import com.arogut.shas.model.RegisterMessage;
import com.arogut.shas.service.DeviceRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class RegistrationControllerIT {

    @Mock
    private DeviceRegistrationService deviceRegistrationService;

    @InjectMocks
    private RegistrationController registrationController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void shouldRegisterDevice200OK() throws Exception {
        RegisterMessage message = new RegisterMessageBuilder()
                .withDeviceName("test")
                .withHost("127.0.0.1")
                .withDeviceType(DeviceType.SOURCE)
                .withFields(Collections.singletonMap("temp",1))
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(message);

        when(deviceRegistrationService.register(Matchers.any())).thenReturn(Optional.of("new-pretty-unique-id"));

        mockMvc.perform(post("/register").content(jsonString).header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }
}

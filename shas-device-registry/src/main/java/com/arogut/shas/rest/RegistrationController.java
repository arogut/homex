package com.arogut.shas.rest;

import com.arogut.shas.model.RegisterSuccessfulMessage;
import com.arogut.shas.model.RegisterMessage;
import com.arogut.shas.service.DeviceRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final DeviceRegistrationService deviceRegistrationService;

    @Autowired
    public RegistrationController(DeviceRegistrationService deviceRegistrationService) {
        this.deviceRegistrationService = deviceRegistrationService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid RegisterMessage message) {
        return deviceRegistrationService.register(message).map(x -> {
            RegisterSuccessfulMessage succ = RegisterSuccessfulMessage.REGISTRATION_COMPLETED;
            succ.setDeviceId(x);
            return ResponseEntity.ok(succ);
        }).orElse(ResponseEntity.badRequest().build());
    }
}

package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceRegistrationService;
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
    public ResponseEntity<?> register(@RequestBody @Valid Device message) {
        return deviceRegistrationService.register(message).map(x -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.badRequest().build());
    }
}

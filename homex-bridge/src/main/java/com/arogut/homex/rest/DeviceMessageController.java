package com.arogut.homex.rest;

import com.arogut.homex.model.DeviceMessage;
import com.arogut.homex.service.DeviceMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/devices/message")
public class DeviceMessageController {

    private final DeviceMessageService deviceMessageService;

    public DeviceMessageController(DeviceMessageService deviceMessageService) {
        this.deviceMessageService = deviceMessageService;
    }

    @PostMapping
    public void receiveMessage(@Valid DeviceMessage message) {
        deviceMessageService.handle(message);
    }
}

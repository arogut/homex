package com.arogut.homex.rest;

import com.arogut.homex.model.DeviceMessage;
import com.arogut.homex.service.DeviceMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/devices/message")
@RequiredArgsConstructor
public class DeviceMessageController {

    private final DeviceMessageService deviceMessageService;

    @PostMapping
    public Mono<ResponseEntity<Void>> receiveMessage(@RequestBody DeviceMessage message) {
        deviceMessageService.handle(message);
        return Mono.just(ResponseEntity.accepted().build());
    }
}

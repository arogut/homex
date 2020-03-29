package com.arogut.homex.data.api;

import com.arogut.homex.data.model.DeviceMessage;
import com.arogut.homex.data.service.DeviceMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/devices/message")
@RequiredArgsConstructor
public class DeviceMessageController {

    private final DeviceMessageService deviceMessageService;

    @PostMapping
    public Mono<ResponseEntity<Void>> receiveMessage(@Valid @RequestBody DeviceMessage message) {
        deviceMessageService.handle(message);
        return Mono.just(ResponseEntity.accepted().build());
    }
}

package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public Mono<ResponseEntity<Void>> addDevice(@Valid @RequestBody Device device) {
        return Mono.just(ResponseEntity.created(URI.create("/devices/" + deviceService.add(device).block())).build());
    }

    @GetMapping
    public Mono<ServerResponse> getDevices() {
        return ServerResponse.ok().body(deviceService.getAll(), Device.class);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Device>> getDeviceById(@PathVariable String id) {
        return deviceService.getById(id)
                .map(d -> ResponseEntity.ok().body(d))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

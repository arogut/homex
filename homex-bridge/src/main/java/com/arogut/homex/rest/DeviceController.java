package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public Mono<ServerResponse> addDevice(@RequestBody Device device) {
        return ServerResponse.created(URI.create("/devices/" + deviceService.add(device))).build();
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

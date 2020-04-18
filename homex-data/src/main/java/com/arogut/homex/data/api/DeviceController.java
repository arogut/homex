package com.arogut.homex.data.api;

import com.arogut.homex.data.model.Device;
import com.arogut.homex.data.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public Mono<ResponseEntity<Device>> add(@Valid @RequestBody Device device) {
        return deviceService.add(device)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<Device> getDevices() {
        return deviceService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Device>> getDeviceById(@PathVariable String id) {
        return deviceService.getById(id)
                .map(d -> ResponseEntity.ok().body(d))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

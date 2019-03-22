package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        return ResponseEntity.created(URI.create("/devices/" + deviceService.add(device))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable("id") String id) {
        return deviceService.getById(id).map(x -> status(HttpStatus.OK).body(x))
                .orElse(status(HttpStatus.NOT_FOUND).build());
    }
}

package com.arogut.shas.rest;

import com.arogut.shas.model.Device;
import com.arogut.shas.service.DeviceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceProviderService deviceProviderService;

    @Autowired
    public DeviceController(DeviceProviderService deviceProviderService) {
        this.deviceProviderService = deviceProviderService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Device>> getDevices() {
        return ResponseEntity.ok(deviceProviderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable("id") String id) {
        return deviceProviderService.getById(id).map(x -> status(HttpStatus.OK).body(x))
                .orElse(status(HttpStatus.NOT_FOUND).build());
    }
}

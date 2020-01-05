package com.arogut.homex.rest;

import com.arogut.homex.model.Device;
import com.arogut.homex.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class DeviceHandler {

    private final DeviceService deviceService;

    @Autowired
    public DeviceHandler(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public Mono<ServerResponse> addDevice(ServerRequest request) {
        return ServerResponse.created(URI.create("/devices/" + deviceService.add(request.bodyToMono(Device.class)))).build();
    }

    public Mono<ServerResponse> getDevices(ServerRequest request) {
        return ServerResponse.ok().body(deviceService.getAll(), Device.class);
    }

    public Mono<ServerResponse> getDeviceById(ServerRequest request) {
        return deviceService.getById(request.pathVariable("id"))
                .flatMap(d -> ServerResponse.ok().bodyValue(d))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}

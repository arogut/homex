package com.arogut.homex.edge.client;

import com.arogut.homex.edge.model.DeviceMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "BridgeClient", url="${bridge.url}")
public interface BridgeClient {

    @PostMapping("/devices/message")
    String sendMessage(@Valid @RequestBody DeviceMessage message);
}

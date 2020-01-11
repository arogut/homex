package com.arogut.homex.edge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class DeviceMessage {
    private String deviceId;
    private long measuredTime;
    private List<Measurement> data;
}

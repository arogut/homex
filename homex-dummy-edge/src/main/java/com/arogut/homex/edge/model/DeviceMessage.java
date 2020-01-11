package com.arogut.homex.edge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceMessage {
    private String deviceId;
    private long measuredTime;
    private List<Measurement> data;
}

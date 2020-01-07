package com.arogut.homex.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMessage {

    private Long id;
    private String deviceId;
    private long measuredTime;
    private long receivedTime;
    private List<Measurement> data;
}

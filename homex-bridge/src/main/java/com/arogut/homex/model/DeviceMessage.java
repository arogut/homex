package com.arogut.homex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class DeviceMessage {

    private Long id;
    private String deviceId;
    private long measuredTime;
    private long receivedTime;
    private List<Measurement> data;
}

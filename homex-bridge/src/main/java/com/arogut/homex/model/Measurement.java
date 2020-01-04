package com.arogut.homex.model;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;

@Getter
@Setter
@org.influxdb.annotation.Measurement(name = "measurement")
public class Measurement {
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
}

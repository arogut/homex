package com.arogut.homex.model;

import lombok.*;
import org.influxdb.annotation.Column;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@org.influxdb.annotation.Measurement(name = "measurement")
public class Measurement {
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
}

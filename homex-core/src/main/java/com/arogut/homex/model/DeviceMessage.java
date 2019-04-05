package com.arogut.homex.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device_measurement")
@Getter
@Setter
public class DeviceMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String deviceId;
    @Column
    private long measuredTime;
    @Column
    private long receivedTime;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Measurement> data;
}

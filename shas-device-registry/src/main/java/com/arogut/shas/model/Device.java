package com.arogut.shas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "deviceType")
@Getter
@Setter
public class Device {
    @Id
    @NotEmpty
    private String id;
    @Column
    private String name;
    @Column(name = "deviceType", insertable = false, updatable = false)
    private DeviceType deviceType;
    @Column
    private boolean isConnected;
    @Column
    private String host;
    @Column
    private int port;
}

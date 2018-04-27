package com.arogut.shas.model.jpa.entity;

import com.arogut.shas.model.DeviceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "deviceType")
@Getter
@Setter
public abstract class Device {
    @Id
    @NotEmpty
    private String id;
    @Column
    private String name;
    @Column(name = "deviceType",insertable = false, updatable = false)
    private DeviceType deviceType;
    @Column
    private boolean isConnected;
    @Column
    private String host;
    @Column
    private int port;
    @Column
    private Instant lastConnection;
}

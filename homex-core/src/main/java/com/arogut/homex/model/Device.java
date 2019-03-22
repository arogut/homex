package com.arogut.homex.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "deviceType")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
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
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Measurement> measurements;
}

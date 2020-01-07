package com.arogut.homex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "deviceType")
@Getter
@Setter
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
    @JsonProperty
    private boolean isConnected;
    @Column
    private String host;
    @Column
    private int port;
}

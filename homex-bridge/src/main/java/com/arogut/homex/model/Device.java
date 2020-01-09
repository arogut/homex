package com.arogut.homex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @GeneratedValue(generator = "UUID")
    private String id;
    @Column
    @NotEmpty
    private String name;
    @Column(name = "deviceType", insertable = false, updatable = false)
    @NotNull
    private DeviceType deviceType;
    @Column
    @JsonProperty
    private boolean isConnected;
    @Column
    @NotEmpty
    private String host;
    @Column
    @Max(99999)
    private int port;
}

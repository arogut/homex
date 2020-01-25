package com.arogut.homex.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    @NotEmpty
    private String macAddress;
    @Column
    @NotEmpty
    private String name;
    @Enumerated
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

package com.arogut.homex.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String id;

    @NotEmpty
    private String macAddress;

    @NotEmpty
    private String name;

    @NotNull
    private DeviceType deviceType;

    private boolean isConnected;

    @NotEmpty
    private String host;

    @Positive
    @Max(99999)
    private int port;

    private Set<@Valid Measurement> measurements = new HashSet<>();

    private Set<@Valid Command> commands = new HashSet<>();
}

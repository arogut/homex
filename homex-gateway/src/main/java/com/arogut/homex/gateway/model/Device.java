package com.arogut.homex.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {

    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private DeviceType deviceType;
    @JsonProperty
    private boolean isConnected;
    @NotEmpty
    private String host;
    @Max(99999)
    private int port;
}

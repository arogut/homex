package com.arogut.homex.edge.config.properties;

import com.arogut.homex.edge.model.DeviceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DeviceProperties {

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

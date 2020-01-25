package com.arogut.homex.edge.config.properties;

import com.arogut.homex.edge.Utils;
import com.arogut.homex.edge.model.DeviceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "edge.contract.device")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Component
public class DeviceProperties {

    private String id;
    @NotEmpty
    private String macAddress;
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

    @PostConstruct
    public void setUpMacAddress() {
        macAddress = Utils.getMacAddress();
    }
}

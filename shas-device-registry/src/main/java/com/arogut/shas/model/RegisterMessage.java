package com.arogut.shas.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Map;

@Getter
@Setter
public class RegisterMessage {

    private static final String VALID_IP = "^(([0-9]|[1-9][0-9]|1[0-9]{2}" +
            "|2[0-4][0-9]|25[0-5])\\.){3}" +
            "([0-9]|[1-9][0-9]|1[0-9]{2}" +
            "|2[0-4][0-9]|25[0-5])$";

    @NotNull
    @Size(min = 3, max = 12)
    private String deviceName;

    @NotNull
    @Pattern(regexp = VALID_IP)
    private String host;

    @NotNull
    @Min(0)
    @Max(65535)
    private int port;

    private DeviceType deviceType;

    @NotEmpty
    private Map<String, Object> fields;
}

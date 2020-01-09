package com.arogut.homex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMessage {
    @NotBlank
    private String deviceId;
    // TODO: 09.01.2020 Create validation for long timestamps
    private long measuredTime;
    private long receivedTime;
    @NotEmpty
    private List<Measurement> data;
}

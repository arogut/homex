package com.arogut.homex.bridge.model;

import com.arogut.homex.bridge.validation.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceMessage {
    @NotBlank
    private String deviceId;
    @Past
    private long measuredTime;
    private long receivedTime = System.currentTimeMillis();
    @NotEmpty
    private List<Measurement> data;
}

package com.arogut.homex.model;

import com.arogut.homex.validation.ExistingDevice;
import com.arogut.homex.validation.Past;
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
    @ExistingDevice
    private String deviceId;
    @Past
    private long measuredTime;
    @Past
    private long receivedTime;
    @NotEmpty
    private List<Measurement> data;
}

package com.arogut.homex.edge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
public class Measurement {
    @NotNull
    private String name;
    @NotNull
    private Number value;
}

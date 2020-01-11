package com.arogut.homex.edge.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class Measurement {
    @NotNull
    private String name;
    @NotNull
    private Number value;
}

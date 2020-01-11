package com.arogut.homex.edge.model;

import com.arogut.homex.edge.service.generator.Generators;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeasurementType {
    INT(Generators.IntGenerator.get()),
    DOUBLE(Generators.DoubleGenerator.get());

    private final Generators.Generator generator;
}

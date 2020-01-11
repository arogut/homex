package com.arogut.homex.edge.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class NumberMeasurement extends Measurement {
        private int min;
        private int max;
}

package com.arogut.homex.edge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
public class NumberMeasurement extends Measurement {
        private int min;
        private int max;
}

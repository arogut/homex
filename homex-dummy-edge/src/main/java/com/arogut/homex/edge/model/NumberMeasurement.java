package com.arogut.homex.edge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class NumberMeasurement extends Measurement {

        private int min;
        private int max;
}

package com.arogut.homex.edge.service.generator;

import com.arogut.homex.edge.model.NumberMeasurement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class GeneratorsTest {

    @Test
    void shouldReturnCorrectGenerator() {
        Assertions.assertThat(Generators.IntGenerator.get().getClass()).isEqualTo(Generators.IntGenerator.class);
        Assertions.assertThat(Generators.DoubleGenerator.get().getClass()).isEqualTo(Generators.DoubleGenerator.class);
    }

    @Test
    void shouldGenerateIntFromRange() {
        NumberMeasurement numberMeasurement = NumberMeasurement.builder().min(10).max(15).build();
        IntStream.range(0, 10).forEach(i ->
                Assertions.assertThat((int)Generators.IntGenerator.get().generateValue(numberMeasurement).getValue())
                        .isBetween(numberMeasurement.getMin(), numberMeasurement.getMax())
        );
    }

    @Test
    void shouldGenerateDoubleFromRange() {
        NumberMeasurement numberMeasurement = NumberMeasurement.builder().min(10).max(15).build();
        IntStream.range(0, 10).forEach(i ->
                Assertions.assertThat((double) Generators.DoubleGenerator.get().generateValue(numberMeasurement).getValue())
                        .isBetween((double) numberMeasurement.getMin(), (double) numberMeasurement.getMax())
        );
    }
}

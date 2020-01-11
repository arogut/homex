package com.arogut.homex.edge.service.generator;

import com.arogut.homex.edge.model.NumberMeasurement;
import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum Generators implements Supplier<Generators.Generator> {
    IntGenerator(IntGenerator::new),
    DoubleGenerator(DoubleGenerator::new);

    private final Supplier<Generator> factory;

    @Override
    public Generator get() {
        return factory.get();
    }

    public interface Generator {
        NumberMeasurement generateValue(NumberMeasurement measurement);
    }

    static class IntGenerator implements Generator {
        @Override
        public NumberMeasurement generateValue(NumberMeasurement measurement) {
            Random r = new Random();
            measurement.setValue(r.nextInt((measurement.getMax() - measurement.getMin()) + 1) + measurement.getMin());
            return measurement;
        }
    }

    static class DoubleGenerator implements Generator {
        @Override
        public NumberMeasurement generateValue(NumberMeasurement measurement) {
            Random r = new Random();
            measurement.setValue(measurement.getMin() + (measurement.getMax() - measurement.getMin()) * r.nextDouble());
            return measurement;
        }
    }
}

package com.arogut.homex.bridge.validation;

import com.arogut.homex.bridge.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
public class ExistingDeviceValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private ExistingDeviceValidator validator;

    @Test
    void shouldReturnFalseWhenFutureTimestamp() {
        Mockito.when(deviceRepository.existsById("dummy")).thenReturn(true);

        Assertions.assertTrue(validator.isValid("dummy", context));
        Assertions.assertFalse(validator.isValid("invalid", context));
        Assertions.assertFalse(validator.isValid(null, context));
    }
}

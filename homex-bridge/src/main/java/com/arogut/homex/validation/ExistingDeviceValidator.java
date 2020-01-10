package com.arogut.homex.validation;

import com.arogut.homex.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingDeviceValidator implements ConstraintValidator<ExistingDevice, String> {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return deviceRepository.existsById(value);
    }
}

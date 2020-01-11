package com.arogut.homex.bridge.validation;

import com.arogut.homex.bridge.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class ExistingDeviceValidator implements ConstraintValidator<ExistingDevice, String> {

    private final DeviceRepository deviceRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return deviceRepository.existsById(value);
    }
}

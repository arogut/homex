package com.arogut.homex.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ExistingDeviceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingDevice {
    String message() default "Timestamp in long should be past date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

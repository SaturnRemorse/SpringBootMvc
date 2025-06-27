package com.saturn.SpringBootMvc.SpringBootMvc.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(
        validatedBy = {EmployeeAgeValidator.class}
)
public @interface EmployeeAgeValidation {
    String message() default "age should be between 10 and 40";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

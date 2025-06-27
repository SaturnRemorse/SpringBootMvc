package com.saturn.SpringBootMvc.SpringBootMvc.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(
        validatedBy = {EmployeeRoleValidator.class}
)
public @interface EmployeeRoleValidation {
    String message() default "role can be DRIVER OR ENGINEER OR STAFF";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

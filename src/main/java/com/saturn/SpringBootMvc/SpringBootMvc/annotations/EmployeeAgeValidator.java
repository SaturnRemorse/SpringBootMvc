package com.saturn.SpringBootMvc.SpringBootMvc.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator<EmployeeAgeValidation, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if(age == null) return false;
        return (age<=40 && age>=10);
    }
}

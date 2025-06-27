package com.saturn.SpringBootMvc.SpringBootMvc.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s ==null) return false;
        List<String> role = List.of("DRIVER", "ENGINEER", "STAFF");
        return role.contains(s);
    }
}

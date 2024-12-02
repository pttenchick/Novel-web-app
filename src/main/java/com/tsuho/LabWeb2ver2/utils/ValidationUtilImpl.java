package com.tsuho.LabWeb2ver2.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }


    @Override
    public <E> boolean isValid(E object) {
        return this.validator.validate(object).size() == 0;
    }

    @Override
    public Set<ConstraintViolation<Object>> violations(Object object) {
        return this.validator.validate(object);
    }
}


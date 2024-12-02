package com.tsuho.LabWeb2ver2.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Objects;
import java.util.Set;

public interface ValidationUtil {
    <E> boolean isValid(E object);

    Set<ConstraintViolation<Object>> violations(Object object);
}

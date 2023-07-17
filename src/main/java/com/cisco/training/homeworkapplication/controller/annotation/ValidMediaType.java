package com.cisco.training.homeworkapplication.controller.annotation;

import com.cisco.training.homeworkapplication.controller.validator.MultipartFileExtensionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileExtensionValidator.class)
public @interface ValidMediaType {

    String message() default "File must be only file with txt extension";

    String value() default "txt";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


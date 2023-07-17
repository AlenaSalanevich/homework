package com.cisco.training.homeworkapplication.controller.validator;

import com.cisco.training.homeworkapplication.controller.annotation.ValidMediaType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Alena_Salanevich
 */
public class MultipartFileExtensionValidator implements ConstraintValidator<ValidMediaType, MultipartFile> {

    public String allowed;

    @Override
    public void initialize(ValidMediaType constraintAnnotation) {
        allowed = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return allowed.equalsIgnoreCase(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
    }
}

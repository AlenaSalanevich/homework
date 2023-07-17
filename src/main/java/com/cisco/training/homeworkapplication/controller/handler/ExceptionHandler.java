package com.cisco.training.homeworkapplication.controller.handler;

import com.cisco.training.homeworkapplication.model.ErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static java.lang.String.format;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ErrorResponse handleValidationException(ValidationException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ErrorResponse handleIOException(IOException e) {
        return new ErrorResponse(format("Unable to read file: %s", e.getMessage()), HttpStatus.BAD_REQUEST.value());
    }
}

package com.jpmc.theater.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponseModel> constraintViolationHandler(MethodArgumentNotValidException exception) {

        List<String> errors = exception.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        logger.error("Argument Errors: {}", errors.toString());

        List<ErrorModel> errorModels = new ArrayList<ErrorModel>();
        errors.stream().forEach(err -> {
            String[] errDetails = err.split(":");
            ErrorModel errorModel = new ErrorModel(errDetails[0], errDetails[1]);
            errorModels.add(errorModel);
        });

        CustomErrorResponseModel response = new CustomErrorResponseModel("Movie Theater Application", errorModels);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponseModel> constraintViolationHandler(ConstraintViolationException exception) {

        List<String> errors = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        logger.error("Constraint Errors: {}", errors.toString());

        List<ErrorModel> errorModels = new ArrayList<ErrorModel>();
        errors.stream().forEach(err -> {
            String[] errDetails = err.split(":");
            ErrorModel errorModel = new ErrorModel(errDetails[0], errDetails[1]);
            errorModels.add(errorModel);
        });

        CustomErrorResponseModel response = new CustomErrorResponseModel("Movie Theater Application", errorModels);
        return ResponseEntity.badRequest().body(response);
    }

}

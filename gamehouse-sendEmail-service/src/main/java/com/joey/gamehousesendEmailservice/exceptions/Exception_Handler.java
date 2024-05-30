package com.joey.gamehousesendEmailservice.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joey.gamehousesendEmailservice.exceptions.recrods.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Exception_Handler extends ResponseEntityExceptionHandler  {

    private final Logger LOGGER = LoggerFactory.getLogger(Exception_Handler.class);

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> runtimeExceptionHandler (RuntimeException exception) {
        ErrorResponse<String> threatResponse = new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        LOGGER.error("Error: {}", exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body(threatResponse.body());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> exceptionHandler (Exception exception) {
        ErrorResponse<String> threatResponse = new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        LOGGER.error("Error: {}", exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body(threatResponse.body());
    }

    @ExceptionHandler(JsonProcessingException.class)
    private ResponseEntity<String> jsonProcessingExceptionHandler (JsonProcessingException exception) {
        ErrorResponse<String> threatResponse = new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        LOGGER.error("Error in serializer an suggestion to JSON: {}", exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body(threatResponse.body());
    }

    @ExceptionHandler(EmailServiceException.class)
    private ResponseEntity<String> emailServiceExceptionHandler (EmailServiceException exception) {
        ErrorResponse<String> threatResponse = new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        LOGGER.error("Error in serializer an suggestion to JSON: {}", exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body(threatResponse.body());
    }
}


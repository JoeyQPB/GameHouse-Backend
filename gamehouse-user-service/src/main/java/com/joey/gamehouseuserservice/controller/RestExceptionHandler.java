package com.joey.gamehouseuserservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joey.gamehouseuserservice.exceptions.BadRequestException;
import com.joey.gamehouseuserservice.exceptions.ExistingUserException;
import com.joey.gamehouseuserservice.exceptions.MissingFieldsException;
import com.joey.gamehouseuserservice.exceptions.UserNotFoundException;
import com.joey.gamehouseuserservice.service.enums.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> exceptionHandler (Exception exception) {
        LOGGER.error("Error: Exception - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> runtimeExceptionHandler (RuntimeException exception) {
        LOGGER.error("Error: RuntimeException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<String> nullPointerExceptionHandler (NullPointerException exception) {
        LOGGER.error("Error: NullPointerException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(MissingFieldsException.class)
    private ResponseEntity<String> missingFieldsExceptionHandler (MissingFieldsException exception) {
        LOGGER.error("Error: MissingFieldsException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<String> badRequestExceptionHandler (BadRequestException exception) {
        LOGGER.error("Error: BadRequestException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(ExistingUserException.class)
    private ResponseEntity<String> existingUserExceptionHandler (ExistingUserException exception) {
        LOGGER.error("Error: ExistingUserException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundExceptionHandler (UserNotFoundException exception) {
        LOGGER.error("Error: UserNotFoundException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(JsonProcessingException.class)
    private ResponseEntity<String> jsonProcessingExceptionHandler (JsonProcessingException exception) {
        LOGGER.error("Error: JsonProcessingException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }

    @ExceptionHandler(IOException.class)
    private ResponseEntity<String> IOExceptionHandler (IOException exception) {
        LOGGER.error("Error: IOException - " + exception.getMessage());
        ServiceResponse<String> threatResponse = new ServiceResponse<>(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(threatResponse.httpStatus()).body("@ControllerAdvice: " + threatResponse.body());
    }
}

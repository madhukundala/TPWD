package com.infosys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlers
{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleServiceException(ServiceException ex)
    {
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleNumberFormatException(NumberFormatException ex)
    {
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid Input " + ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleClassCastException(ClassCastException ex)
    {
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid Input " + ex.getMessage()));
    }


}
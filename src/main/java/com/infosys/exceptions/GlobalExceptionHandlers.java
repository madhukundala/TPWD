package com.infosys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid input " + ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleClassCastException(ClassCastException ex)
    {
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid Input " + ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleIllegalArgumentException(IllegalArgumentException ex)
    {
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid character found in the request " + ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResource> handleMissingParams(MissingServletRequestParameterException ex)
    {
        String name = ex.getParameterName();
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid input " + ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResource> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex)
    {
        Object name = ex.getValue();
        return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid input " + name));
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResource> handleException(Exception ex)
    {
        if (ex.getClass().isInstance(NumberFormatException.class))
        {
            return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid input " + ex.getMessage()));
        }
        else if (ex.getClass().isInstance(IllegalArgumentException.class))
        {
            return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid input " + ex.getMessage()));
        }

        else if (ex.getClass().isInstance(ClassCastException.class))
        {
            return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.BAD_REQUEST.value(), "Invalid input " + ex.getMessage()));
        }
        else
        {
            return ResponseEntity.badRequest().body(new ErrorResource(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()));
        }
    }

}
package com.vr.miniauth.exception;

import com.vr.miniauth.common.response.BaseReturn;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {

        LOGGER.error("Handle Exception ConstraintViolationException", exception);

        BaseReturn<Object> response = new BaseReturn<Object>();
        response.success = false;
        response.description = exception.getMessage();

        LOGGER.info("Handle Exception ConstraintViolationException Response: {}", response);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(HttpMessageConversionException exception) {

        LOGGER.error("Handle Exception HttpMessageConversionException", exception);

        BaseReturn<Object> response = new BaseReturn<Object>();
        response.success = false;
        response.description = exception.getMessage();

        LOGGER.info("Handle Exception HttpMessageConversionException Response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
      
        LOGGER.error("Handle Exception HttpMessageNotReadableException", exception);
        BaseReturn<Object> response = new BaseReturn<Object>();

        response.success = false;
        response.description = exception.getMessage();

        LOGGER.info("Handle Exception HttpMessageNotReadableException Response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOGGER.error("Handle Exception ConstraintViolationException", exception);

        FieldError error = (FieldError) exception.getBindingResult().getAllErrors().get(0);

        BaseReturn<Object> response = new BaseReturn<Object>();
        response.success = false;
        response.field = error.getField();
        response.rejectValue = error.getRejectedValue();
        response.error = HttpStatus.BAD_REQUEST.name();
        response.errorCode = HttpStatus.BAD_REQUEST.value();
        response.errorMessage = error.getDefaultMessage();
        response.description = String.format("%s %s", Strings.firstUpperCase(error.getField()), error.getDefaultMessage());

        LOGGER.info("Handle Exception ConstraintViolationException Response: {}", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(BaseException exception, WebRequest request, Locale locale) {

        LOGGER.error("Handle Exception", exception);

        BaseReturn<Object> response = new BaseReturn<Object>();
        response.success = false;
        response.description = exception.getMessage();

        LOGGER.info("Handle Exception Response: {}", response);

        return new ResponseEntity<>(response, exception.getStatus());
    }
}
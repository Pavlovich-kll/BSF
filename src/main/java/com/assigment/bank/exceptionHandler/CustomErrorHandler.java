package com.assigment.bank.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handleRestClientResponseException(RestClientResponseException e) {
        final HttpStatus resolve = HttpStatus.resolve((e.getRawStatusCode()));
        return new ResponseEntity<>(e.getMessage(), resolve == null ? HttpStatus.INTERNAL_SERVER_ERROR : resolve);
    }
}

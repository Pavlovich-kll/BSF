package com.assigment.bank.exceptionHandler;

import com.assigment.bank.exceptionHandler.exception.AccountBalanceException;
import com.assigment.bank.exceptionHandler.exception.AccountNotFoundException;
import com.assigment.bank.exceptionHandler.exception.UserNotFoundException;
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

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountException(AccountNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountBalanceException.class)
    public ResponseEntity<String> handleAccountBalanceException(AccountBalanceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handleRestClientResponseException(RestClientResponseException e) {
        final HttpStatus resolve = HttpStatus.resolve((e.getRawStatusCode()));
        return new ResponseEntity<>(e.getMessage(), resolve == null ? HttpStatus.INTERNAL_SERVER_ERROR : resolve);
    }
}

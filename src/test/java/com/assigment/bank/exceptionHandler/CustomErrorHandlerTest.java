package com.assigment.bank.exceptionHandler;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorHandlerTest {

    private final CustomErrorHandler handler = new CustomErrorHandler();

    @Test
    public void handleResponseStatusException() {
        final ResponseEntity<String> stringResponseEntity = handler.handleResponseStatusException(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some exception message"));
        Assert.assertNotNull(stringResponseEntity.getStatusCode());
        Assert.assertNotNull(stringResponseEntity.getBody());
    }

    @Test
    public void handleRestClientResponseException() {
        ResponseEntity<String> response = handler.handleRestClientResponseException(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "error"));
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
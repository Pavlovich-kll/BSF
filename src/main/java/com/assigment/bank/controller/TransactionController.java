package com.assigment.bank.controller;

import com.assigment.bank.dto.TransactionDto;
import com.assigment.bank.service.implServices.PlusTransactionServiceImpl;
import com.assigment.bank.service.implServices.MinusTransactionServiceImpl;
import com.assigment.bank.service.implServices.TransferTransactionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "transaction", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Transaction operation endpoints"})
public class TransactionController {

    @Autowired
    private PlusTransactionServiceImpl toUpService;
    @Autowired
    private MinusTransactionServiceImpl withdrawService;
    @Autowired
    private TransferTransactionServiceImpl transactionService;

    @PostMapping(path = "/toUp")
    @ApiOperation(value = "Top up your account balance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<String> plusTransaction(@RequestBody TransactionDto transaction) {
        toUpService.doOperation(transaction);
        return new ResponseEntity<>(
                "You have topped up your balance on " + transaction.getTxAmount(),
                HttpStatus.OK);
    }

    @PostMapping(path = "/withdraw")
    @ApiOperation(value = "Withdraw your account balance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<String> minusTransaction(@RequestBody TransactionDto transaction) {
        withdrawService.doOperation(transaction);
        return new ResponseEntity<>(
                "You have withdrawn " + transaction.getTxAmount() + " from your balance!" ,
                HttpStatus.OK);
    }

    @PostMapping(path = "/transfer")
    @ApiOperation(value = "Transfer transaction from one account to another")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<String> transferTransaction(@RequestBody TransactionDto transaction) {
        transactionService.doTransfer(transaction, transaction.getToAccountNumber());
        return new ResponseEntity<>(
                "The transaction from account " + transaction.getAccountNumber() +
                        " to account " + transaction.getToAccountNumber() + " was successful!" ,
                HttpStatus.OK);
    }
}

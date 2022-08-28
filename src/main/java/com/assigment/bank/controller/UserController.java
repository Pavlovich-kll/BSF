package com.assigment.bank.controller;

import com.assigment.bank.dto.UserDto;
import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.model.User;
import com.assigment.bank.service.implServices.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"User operation endpoints"})
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path = "/all")
    @ApiOperation(value = "Find all users", notes = "Gets details of all the users")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public List<User> getAllCUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(path = "/create")
    @ApiOperation(value = "Add a user", notes = "Add user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
        Long userNumber = userService.createUser(user);
        return new ResponseEntity<>("User with number №" + userNumber +"  created!", HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userNumber}")
    @ApiOperation(value = "Get user details", notes = "Get user details by user number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<User> getUser(@PathVariable Long userNumber) {
        User foundUser = userService.getUsersByNumber(userNumber);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{userNumber}")
    @ApiOperation(value = "Delete user and related account", notes = "Delete user and account.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Resource deleted successfully", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<Void> deleteUser(@PathVariable Long userNumber) {
        userService.deleteUserAndAccount(userNumber);
        return ResponseEntity.ok().build();
    }
}


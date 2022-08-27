package com.assigment.bank.controller;

import com.assigment.bank.entity.UserEntity;
import com.assigment.bank.exception.UserNotFountException;
import com.assigment.bank.model.User;
import com.assigment.bank.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("user")
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

    @PostMapping(path = "/create")
    @ApiOperation(value = "Add a user", notes = "Add user and create an account")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<UserEntity> createUser(@RequestBody User user) {
        userService.updateOrCreateUser(user);
        return new ResponseEntity<>(userService.getUsersByNumber(user.getUserNumber()), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{userNumber}")
    @ApiOperation(value = "Get user details", notes = "Get user details by user number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public UserEntity getUser(@PathVariable Long userNumber) throws UserNotFountException {
        UserEntity foundUser = userService.getUsersByNumber(userNumber);
        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User №" + userNumber + " not found.");
        }
        return foundUser;
    }

    @PutMapping(path = "/update")
    @ApiOperation(value = "Update user", notes = "Update user and account information.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Resource updated successfully", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Void> updateUser(@RequestBody User userInfo) {
        userService.updateOrCreateUser(userInfo);
        return ResponseEntity.ok().build();
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


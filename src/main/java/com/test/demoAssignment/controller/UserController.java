package com.test.demoAssignment.controller;

import com.test.demoAssignment.entity.user.UserRequest;
import com.test.demoAssignment.entity.user.UserResponse;
import com.test.demoAssignment.exception.UserNotFoundException;
import com.test.demoAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserResponse addUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @GetMapping("/user/{id}")
    public UserResponse getUser(@PathVariable("id") String userId) {
        try {
            return userService.getUser(userId);
        }
        catch(UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exc);
        }
    }

    @DeleteMapping("/user/{id}")
    public UserResponse deleteUser(@PathVariable("id") String userId) {
        try {
            return userService.deleteUser(userId);
        }
        catch(UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exc);
        }
    }

    @PutMapping("/user/{id}")
    public UserResponse updateUser(@PathVariable("id") String userId, @Valid @RequestBody UserRequest userRequest) {
        try {
            return userService.updateUser(userId, userRequest);
        }
        catch(UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exc);
        }
    }
}

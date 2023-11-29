package com.projects.tinder.controllers;

import com.projects.tinder.dtos.*;
import com.projects.tinder.entities.Users;
import com.projects.tinder.exceptions.UserNotFound;
import com.projects.tinder.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody @Valid UserRequest request){
        System.out.println(request);
        return service.registerUSer(request);
    }

    @PostMapping("/sign-in")
    public Users login(@RequestBody @Valid UserLogin request){
        return service.login(request);
    }

    @Transactional
    @DeleteMapping("/{username}")
    public UserDTO deleteUserByUsername(@PathVariable @Valid String username){
        return service.deleteUserByUsername(username);
    }

    @GetMapping("/{userId}")
    public Users getUser(@PathVariable Long userId){
        System.out.println("UserId "+ userId);
        return service.getUser(userId);
    }

    @PostMapping("/user/{userId}/interest")
    public InterestDTO addInterests(@PathVariable Long userId, @RequestBody InterestRequest request){
        System.out.println(userId);
        return service.addInterest(userId, request);
    }

    @GetMapping("/user/{userId}")
    public List<InterestRequest> getInterestsOfUser(@PathVariable Long userId){
        return service.getInterestOfUser(userId);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(UserNotFound exception, WebRequest request){
        log.info("Failed to find the requested element", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND);
    }
}

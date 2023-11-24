package com.projects.tinder.controllers;

import com.projects.tinder.dtos.UserDTO;
import com.projects.tinder.dtos.UserRequest;
import com.projects.tinder.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/sign-in")
    public UserDTO registerUser(@RequestBody @Valid UserRequest request){
        System.out.println(request);
        return service.registerUSer(request);
    }
    @GetMapping
    public String getUser(){
        return "Getting All Users";
    }

}

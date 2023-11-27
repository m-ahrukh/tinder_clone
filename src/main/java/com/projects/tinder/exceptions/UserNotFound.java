package com.projects.tinder.exceptions;

import com.projects.tinder.dtos.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}

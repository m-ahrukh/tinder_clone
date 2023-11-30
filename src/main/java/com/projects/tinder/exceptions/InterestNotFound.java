package com.projects.tinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InterestNotFound extends RuntimeException{
    public InterestNotFound(String message){
        super(message);
    }
}

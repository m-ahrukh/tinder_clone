package com.projects.tinder.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty
    private String firstName;
    private String lastName;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String phoneNumber;
    private int agePref;
    private String genderPref;
    private float distancePref;
    private String bio;
}

package com.projects.tinder.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private int agePref;
    private String genderPref;
    private float distancePref;
    private String bio;
    private String lookingFor;

    @OneToMany(mappedBy = "user")
    private List<Interests> interests;
}

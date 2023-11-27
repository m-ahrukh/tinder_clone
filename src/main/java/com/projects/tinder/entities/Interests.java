package com.projects.tinder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String interest;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

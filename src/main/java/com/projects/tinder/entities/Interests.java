package com.projects.tinder.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String interest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

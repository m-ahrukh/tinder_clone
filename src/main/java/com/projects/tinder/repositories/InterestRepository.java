package com.projects.tinder.repositories;

import com.projects.tinder.entities.Interests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interests, Long> {
    List<Interests> findAllByUserId(Long userId);
}

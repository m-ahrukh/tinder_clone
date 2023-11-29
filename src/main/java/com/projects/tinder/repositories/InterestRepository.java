package com.projects.tinder.repositories;

import com.projects.tinder.entities.Interests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interests, Long> {
    List<Interests> findAllByUserId(Long userId);

    Optional<Interests> deleteByUserId(Long userId);
}

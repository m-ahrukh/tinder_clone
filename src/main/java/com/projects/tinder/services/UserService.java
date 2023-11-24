package com.projects.tinder.services;

import com.projects.tinder.dtos.UserDTO;
import com.projects.tinder.dtos.UserRequest;
import com.projects.tinder.entities.Users;
import com.projects.tinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;


    public UserDTO registerUSer(UserRequest request) {

        Users user = mapper.map(request, Users.class);
        repository.save(user);
        return mapper.map(user, UserDTO.class);
    }
}

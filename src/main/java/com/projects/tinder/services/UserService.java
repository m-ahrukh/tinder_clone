package com.projects.tinder.services;

import com.projects.tinder.dtos.*;
import com.projects.tinder.entities.Interests;
import com.projects.tinder.entities.Users;
import com.projects.tinder.exceptions.UserNotFound;
import com.projects.tinder.repositories.InterestRepository;
import com.projects.tinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final InterestRepository interestRepository;
    private final ModelMapper mapper;


    public UserDTO registerUSer(UserRequest request) {

        Users user = mapper.map(request, Users.class);
        repository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    public InterestDTO addInterest(Long id, InterestRequest request) {
        Optional<Users> optionalUser = repository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFound("User not found with id "+ id);
        }

        Users user = optionalUser.get();

        Interests interest = mapper.map(request, Interests.class);

        Users user1 = new Users();
        user1.setId(id);
        interest.setUser(user1);
        interestRepository.save(interest);
        return mapper.map(interest, InterestDTO.class);
    }

    public List<InterestRequest> getInterestOfUser(Long userId) {
        Optional<Users> optionalUser = repository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFound("User not found with id "+  userId);
        }

        Users user = optionalUser.get();
        List<Interests> interests = interestRepository.findAllByUserId(userId);
        List<InterestRequest> interestRequests = new ArrayList<>();

        for (Interests interest : interests){
            if(interest.getUser().getId().equals(userId)){
                interestRequests.add(mapper.map(interest, InterestRequest.class));
            }
        }

        return interestRequests;
    }

    public Users getUser(Long userId) {
        Optional<Users> optionalUser = repository.findById(userId);
        return optionalUser.orElseThrow(() -> new UserNotFound("User not found with id "+ userId));

    }

    public Users login(UserLogin request) {
        Optional<Users> optionalUser = repository.findByUsername(request.getUsername());

        if(optionalUser.isEmpty()){
            throw new UserNotFound("User not found with username "+ request.getUsername());
        }

        return optionalUser.get();
    }

    public UserDTO deleteUserByUsername(String username) {

        Optional<Users> optionalUser = repository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UserNotFound("User not found with username "+ username);
        }

        Users user = optionalUser.get();
        Long userId = user.getId();

        Optional<Interests> optionalInterest = interestRepository.deleteByUserId(userId);
        repository.delete(user);

        return mapper.map(user, UserDTO.class);
    }

    public UserRequest updateUSer(UserRequest request, Long userId) {

        Optional<Users> optionalUser = repository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFound("User not found with id "+userId);
        }

        Users user = optionalUser.get();

        if(request.getFirstName() != null){
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null){
            user.setLastName(request.getLastName());
        }
        if(request.getUsername() != null){
            user.setUsername(request.getUsername());
        }
        if(request.getEmail() != null){
            user.setEmail(request.getEmail());
        }
        if(request.getPassword() != null){
            user.setPassword(request.getPassword());
        }
        if(request.getPhoneNumber() != null){
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getAgePref() > 0){
            user.setAgePref(request.getAgePref());
        }
        if(request.getGenderPref() != null){
            user.setGenderPref(request.getGenderPref());
        }
        if(request.getDistancePref() >= 0){
            user.setDistancePref(request.getDistancePref());
        }
        if(request.getBio() != null){
            user.setBio(request.getBio());
        }
        if(request.getLookingFor() != null){
            user.setLookingFor(request.getLookingFor());
        }
        repository.saveAndFlush(user);
        return mapper.map(user, UserRequest.class);
    }

    public InterestDTO deleteInterest(Long userId, Long interestId) {
        Optional<Interests> optionalInterest = interestRepository.findById(interestId);

        if(optionalInterest.isEmpty()){
            return null;
        }

        Interests interest = optionalInterest.get();
        interestRepository.delete(interest);
        return mapper.map(interest, InterestDTO.class);
    }
}

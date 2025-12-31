package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.SignUpRequestDto;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(SignUpRequestDto signUpRequestDto){
        User user = new User(
                signUpRequestDto.getEmail(),
                signUpRequestDto.getName(),
                signUpRequestDto.getPassword()
        );

        this.userRepository.save(user);
    }
}

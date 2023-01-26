package com.exercise.springsecurity.service;

import com.exercise.springsecurity.dto.UserDto;
import com.exercise.springsecurity.entity.User;
import com.exercise.springsecurity.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var response = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }
}

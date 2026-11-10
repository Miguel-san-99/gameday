package com.soccerapp.gameday.services;

import com.soccerapp.gameday.dtos.RegisterUserRequest;
import com.soccerapp.gameday.enums.Role;
import com.soccerapp.gameday.mappers.UserMapper;
import com.soccerapp.gameday.models.User;
import com.soccerapp.gameday.repositories.TeamRepository;
import com.soccerapp.gameday.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TeamRepository teamRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
    
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + id));
    }
    
    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
    
    @Transactional
    public User saveUserAdmin(RegisterUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }
        User user = userMapper.toEntity(request);
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User saveUserManager(RegisterUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }
        User user = userMapper.toEntity(request);
        user.setRole(Role.MANAGER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, String managerName, String managerPassword) {
        User user = this.getUserById(id);
        user.setName(managerName);
        if (managerPassword != null || !managerPassword.isBlank()) {
            user.setPassword(passwordEncoder.encode(managerPassword));
        }
        return userRepository.save(user);
    }
}
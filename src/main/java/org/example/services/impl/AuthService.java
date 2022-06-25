package org.example.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.services.IAuthService;
import org.example.utils.Role;
import org.example.utils.UserConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Slf4j
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public boolean register(UserDTO userDTO, Role role) {
        if (userRepository.existsByEmail(userDTO.getEmail()))
            return false;

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setBalance(UserConstants.BASE_BALANCE);
        user.setRoles(Collections.singleton(role));

        log.debug("Registered new user: {}", user);
        userRepository.save(user);
        return true;
    }
}

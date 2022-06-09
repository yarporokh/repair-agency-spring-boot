package org.example.services;

import org.example.dto.UserDTO;
import org.example.utils.Role;
import org.springframework.transaction.annotation.Transactional;

public interface IAuthService {
    @Transactional
    public boolean register(UserDTO person, Role role);
}

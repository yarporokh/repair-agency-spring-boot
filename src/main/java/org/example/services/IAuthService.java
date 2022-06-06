package org.example.services;

import org.example.dto.UserDTO;
import org.example.utils.Role;

import javax.transaction.Transactional;

public interface IAuthService {
    @Transactional
    public boolean register(UserDTO person, Role role);
}

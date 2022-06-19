package org.example.services;

import org.example.dto.UserDTO;
import org.example.utils.Role;
import org.springframework.transaction.annotation.Transactional;

/**
 * *IAuthService for connect repository with user controllers.
 * Registration logic
 */
public interface IAuthService {
    /**
     * Method returns user if it in db
     * @param person model of user
     * @param role user registration role
     * @return true if user can register, false if user already exist
     */
    @Transactional
    public boolean register(UserDTO person, Role role);
}

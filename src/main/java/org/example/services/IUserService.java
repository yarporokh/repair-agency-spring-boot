package org.example.services;

import org.example.models.User;
import org.example.utils.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService extends UserDetailsService {
    @Override
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    @Transactional
    void balanceTopUp(User user, double balance);

    @Transactional
    List<User> findByRole(Role role);

    @Transactional
    void saveNewUserBalance(User user, double newBalance);
}

package org.example.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.models.Application;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.services.IUserService;
import org.example.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        log.debug("Logged: {}", user);
        return user;
    }

    @Override
    @Transactional
    public void balanceTopUp(User user, double balance) {
        double userBalance = user.getBalance();
        user.setBalance(userBalance + balance);
        log.debug("Top up balance for user: {}", user);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> findByRole(Role role) {
        List<User> userList = userRepository.findUsersByRoles(role);
        return userList;
    }

    @Override
    @Transactional
    public void saveNewUserBalance(User user, double newBalance) {
        user.setBalance(newBalance);
        log.debug("Balance changed for user: {}", user);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void payApp(User user, Application app) {
        double userBalance = user.getBalance();
        double afterPayBalance = userBalance - app.getPrice();
        log.debug("User: {} payed for application: {}", user, app);
        saveNewUserBalance(user, afterPayBalance);
    }
}

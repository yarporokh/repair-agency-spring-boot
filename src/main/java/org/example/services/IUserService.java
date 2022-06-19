package org.example.services;

import org.example.models.Application;
import org.example.models.User;
import org.example.utils.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IUserService for connect repository with user controllers
 */
public interface IUserService extends UserDetailsService {
    /**
     * Method returns user if it in db
     * @param username email of user
     * @return user with this email
     */
    @Override
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Method for top up money for user
     * @param user the user for whom the balance is topped up
     * @param balance sum for topping up
     */
    @Transactional
    void balanceTopUp(User user, double balance);

    /**
     * Method returns list of users with specific role
     * @param role the role by which the search will take place
     * @return list of users
     */
    @Transactional
    List<User> findByRole(Role role);

    /**
     * Method updates the user balance
     * @param user takes money from user
     * @param newBalance new user balance
     */
    @Transactional
    void saveNewUserBalance(User user, double newBalance);

    /**
     * Method takes money from user for pay for application
     * @param user user who pay
     * @param app application for pay
     */
    @Transactional
    void payApp(User user, Application app);
}

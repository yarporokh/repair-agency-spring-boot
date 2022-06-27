package org.example.services.impl;

import org.example.models.Application;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.utils.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.UserConstants.BASE_BALANCE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void loadUserByUsername() {
        String email = "email@gmail.com";
        User user = new User();
        user.setEmail(email);

        Mockito.doReturn(user).when(userRepository).findByEmail(email);

        UserDetails userDetails = userService.loadUserByUsername(email);

        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);

    }

    @Test
    public void balanceTopUp() {
        User user = new User();
        user.setBalance(BASE_BALANCE);

        userService.balanceTopUp(user, 100);

        Assert.assertTrue(user.getBalance() == 100);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

    @Test
    public void findByRole() {
        List<User> userList = new ArrayList<>();
        Mockito.doReturn(userList).when(userRepository).findUsersByRoles(Role.USER);
        List<User> list = userService.findByRole(Role.USER);

        Mockito.verify(userRepository, Mockito.times(1)).findUsersByRoles(Role.USER);
    }

    @Test
    public void saveNewUserBalance() {
        User user = new User();
        userService.saveNewUserBalance(user, 100);

        Assert.assertTrue(user.getBalance() == 100);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void payApp() {
        double userBalance = 100;
        double applicationPrice = 50;
        User user = new User();
        Application application = new Application();
        user.setBalance(userBalance);
        application.setPrice(applicationPrice);

        double afterPayBalance = userBalance - applicationPrice;

        userService.payApp(user, application);

        Assert.assertTrue(user.getBalance() == afterPayBalance);
    }
}
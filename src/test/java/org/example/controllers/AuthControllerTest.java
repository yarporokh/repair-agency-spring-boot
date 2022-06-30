package org.example.controllers;

import org.example.dto.UserDTO;
import org.example.services.impl.AuthService;
import org.example.utils.Role;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthControllerTest {

    @Autowired
    AuthController authController;

    @MockBean
    AuthService authService;

    @Test
    void getSignPage() {
        String temp = authController.getSignPage();
        Assert.assertEquals("registration", temp);
    }

    @Test
    void signUp() {
        Model model = Mockito.mock(Model.class);
        UserDTO userDTO = new UserDTO("email@gmail.com",
                "123456",
                "Firstname",
                "Lastname");

        Mockito.doReturn(true)
                .when(authService)
                .register(userDTO, Role.USER);

        String temp = authController.signUp("123456", userDTO, model);

        Mockito.verify(authService, Mockito.times(1)).register(userDTO, Role.USER);

        Assert.assertEquals("redirect:/auth/login", temp);

    }

    @Test
    void signUpIncorrectPasswords() {
        Model model = Mockito.mock(Model.class);
        UserDTO userDTO = new UserDTO("email@gmail.com",
                "123456",
                "Firstname",
                "Lastname");
        String temp = authController.signUp("12345", userDTO, model);

        Mockito.verify(authService, Mockito.times(0)).register(userDTO, Role.USER);

        Assert.assertEquals("registration", temp);

    }

    @Test
    void signUpUserExist() {
        Model model = Mockito.mock(Model.class);
        UserDTO userDTO = new UserDTO("email@gmail.com",
                "123456",
                "Firstname",
                "Lastname");
        Mockito.doReturn(false)
                .when(authService)
                .register(userDTO, Role.USER);

        String temp = authController.signUp("12345", userDTO, model);

        Assert.assertEquals("registration", temp);
    }
}
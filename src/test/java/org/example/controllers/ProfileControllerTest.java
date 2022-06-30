package org.example.controllers;

import org.example.services.impl.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProfileControllerTest {
    @Autowired
    ProfileController profileController;

    @MockBean
    UserService userService;


    @Test
    void getProfile() {
        String temp = profileController.getProfile();
        Assert.assertEquals("profile", temp);
    }
}
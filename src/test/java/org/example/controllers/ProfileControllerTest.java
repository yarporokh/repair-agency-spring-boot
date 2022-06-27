package org.example.controllers;

import org.example.services.impl.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

/*    @Test
    void getBalancePage() {
        String temp = profileController.getBalancePage();
        Assert.assertEquals("balance", temp);
    }*/

    @Test
    void topUpBalance() {
/*        Model model = Mockito.mock(Model.class);
        User user = new User();
        user.setBalance(0);
        String temp = profileController.topUpBalance(model, 100);

        Assert.assertEquals("redirect:/user/profile", temp);

        Mockito.verify(userService, Mockito.times(1)).balanceTopUp(user, 100);*/
    }
}
package org.example.utils;

import org.example.models.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setRoles(Collections.singleton(Role.USER));
        System.out.println(user);
        System.out.println(!user.getRoles().contains(Role.MANAGER));
    }
}

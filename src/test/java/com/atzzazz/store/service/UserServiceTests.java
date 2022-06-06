package com.atzzazz.store.service;

import com.atzzazz.store.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void register(){
        User user = new User();
        user.setUserName("tanaka");
        user.setPassword("123");

        userService.register(user);
    }
}

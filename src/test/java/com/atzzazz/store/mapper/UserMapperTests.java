package com.atzzazz.store.mapper;

import com.atzzazz.store.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest：表示标注当前类是测试类，不会随同项目一块打包
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("tom");
        user.setPassword("123456");
        Integer rows = userMapper.insertUser(user);
        System.out.println(rows);
    }

    @Test
    public void findByUserName(){
        User user = userMapper.findByUserName("tom");
        System.out.println(user);
    }
}

package com.atzzazz.store.mapper;

import com.atzzazz.store.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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

    @Test
    public void updateUserPassword(){
        Date date = new Date();
        Integer rows = userMapper.updateUserPassword(3, "123", "tom", date);
        System.out.println(rows);
    }

    @Test
    public void findByUserId(){
        User user = userMapper.findByUserId(6);
        System.out.println(user);
    }

    @Test
    public void updateInfoByUserID(){
        User user = userMapper.findByUserId(14);
        user.setPhone(null);
        user.setEmail(null);
        user.setGender(null);
        user.setModifiedUser(user.getUserName());
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUserID(user);
        System.out.println(rows);
    }
}

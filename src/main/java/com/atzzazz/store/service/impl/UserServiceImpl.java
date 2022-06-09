package com.atzzazz.store.service.impl;

import com.atzzazz.store.mapper.UserMapper;
import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.IUserService;
import com.atzzazz.store.service.ex.*;
import com.atzzazz.store.util.JsonResult;
import com.atzzazz.store.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.sql.rowset.serial.SerialException;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {

        String userName = user.getUserName();

        //ユーザー名が既に存在するかチェックする
        User userByUserName = userMapper.findByUserName(userName);

        //ユーザー名が重複しているかをチェックする
        if (userByUserName != null) {
            throw new UsernameDuplicateException("ユーザー名が既に存在しています");
        }

        // 创建当前时间对象
        Date date = new Date();

        // 补全数据：盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);

        // 补全数据：加密后的密码
        String oldPassword = user.getPassword();
        String md5Password = getMd5Password(oldPassword, salt);
        user.setPassword(md5Password);

        // 补全数据：isDelete(0)
        user.setIsDelete(0);

        // 补全数据：4项日志属性
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        user.setModifiedUser(user.getUserName());
        user.setCreatedUser(user.getUserName());


        //ユーザー名が存在いない時新規登録できるようにする
        Integer rows = userMapper.insertUser(user);

        //ユーザーをデータベースにinsertする際に未知のエラーが発生した際にException
        if (rows != 1) {
            throw new InsertException("サーバー未知のエラーが発生しています。もう一度お試しください");
        }

    }

    @Override
    public User login(String userName, String password) {

        User result = userMapper.findByUserName(userName);

        //Userが存在するかを判断する。nullの場合、ユーザーが存在しない
        if (result == null) {
            throw new UserNotFoundException("ユーザーデータが存在しません");
        }

        //userが削除されているかを判断する
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("ユーザーデータが存在しません");
        }

        //passwordが一致するかを判断する
        String salt = result.getSalt();
        String newMd5Password = getMd5Password(password, salt);
        String oldPassword = result.getPassword();
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("passwordが間違っています");
        }

        //必要なデータだけ収納するようにする
        User user = new User();
        user.setUserName(result.getUserName());
        user.setUserId(result.getUserId());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer userId, String userName, String oldPassword, String newPassword) {
        User result = userMapper.findByUserId(userId);

        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("ユーザーが存在しません");
        }

        String oldMd5Password = getMd5Password(oldPassword, result.getSalt());

        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("パースワードが一致しません");
        }

        String newMd5Password = getMd5Password(newPassword, result.getSalt());

        Integer rows = userMapper.updateUserPassword(userId, newMd5Password, userName, new Date());

        Util.checkoutRows(rows);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        User result = userMapper.findByUserId(userId);
        Util.userIsFound(result, result.getIsDelete());

        User user = new User();
        user.setUserName(result.getUserName());
        user.setEmail(result.getEmail());
        user.setPhone(result.getPhone());
        user.setGender(result.getGender());

        return user;
    }

    @Override
    public void changeUserInfo(Integer userId, User user) {
        User result = userMapper.findByUserId(userId);
        Util.userIsFound(result,result.getIsDelete());

        user.setUserId(userId);
        user.setModifiedUser(result.getUserName());
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUserID(user);

        Util.checkoutRows(rows);

    }

    /**
     * MD5
     *
     * @param password
     * @param salt
     * @return 暗号化した password
     */
    private String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}

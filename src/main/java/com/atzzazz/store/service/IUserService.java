package com.atzzazz.store.service;

import com.atzzazz.store.pojo.User;
import com.atzzazz.store.util.JsonResult;

/**
 * ユーザー登録業務を処理するinterface
 */
public interface IUserService {

    void register(User user);

    User login(String userName,String password);

    void changePassword(Integer userId,
                        String userName,
                        String oldPassword,
                        String newPassword);

    User getUserByUserId(Integer userId);

    void changeUserInfo(Integer userId, User user);

    void changeAvatar(Integer userId, String username, String avatar);
}

package com.atzzazz.store.service;

import com.atzzazz.store.pojo.User;

/**
 * ユーザー登録業務を処理するinterface
 */
public interface IUserService {

    public void register(User user);

    User login(String userName,String password);
}

package com.atzzazz.store.mapper;

import com.atzzazz.store.pojo.User;

import java.util.Date;

/**
 * 　サインアップする歳のinterface
 */
public interface UserMapper {
    /**
     * ユーザーを追加
     *
     * @param user
     * @return　影響を受けた行数
     */
    Integer insertUser(User user);

    /**
     * ユーザー名でユーザーが既に存在しているかを調べる
     *
     * @param username
     * @return　nullの場合ユーザー登録できる、nullではない場合ユーザー登録はできない
     */
    User findByUserName(String username);

    /**
     * ユーザーIDでユーザーパスワードを修正する
     *
     * @param userId
     * @param newUserPassword
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateUserPassword(Integer userId,
                               String newUserPassword,
                               String modifiedUser,
                               Date modifiedTime);

    /**
     * ユーザーIDでユーザーを探す
     *
     * @param userId
     * @return　ユーザーのインスタンス
     */
    User findByUserId(Integer userId);

}

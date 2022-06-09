package com.atzzazz.store.util;

import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.ex.UpdateException;
import com.atzzazz.store.service.ex.UserNotFoundException;

import javax.sql.rowset.serial.SerialException;

public class Util {

    public static void userIsFound(User user, Integer isDelete) {
        if (user == null || isDelete == 1) {
            throw new UserNotFoundException("ユーザーのデータが存在しません");
        }
    }

    public static void checkoutRows(Integer rows) {
        if (rows != 1) {
            throw new UpdateException("データを更新する際に未知のエラーが発生しています");
        }
    }
}

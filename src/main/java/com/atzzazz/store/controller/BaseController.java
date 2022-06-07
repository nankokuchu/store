package com.atzzazz.store.controller;

import com.atzzazz.store.service.ex.*;
import com.atzzazz.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.sql.rowset.serial.SerialException;

/**
 * Base　controller
 */
public class BaseController {
    //操作が成功した際のステータスコード
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException){
            result.setCode(4000);
            result.setMessage("ユーザー名が重複されています");
        }else if (e instanceof InsertException){
            result.setCode(5000);
            result.setMessage("サーバー側に未知のエラーが発生しています");
        }else if (e instanceof PasswordNotMatchException){
            result.setCode(6000);
            result.setMessage("パースワードが間違っています");
        }else if (e instanceof UserNotFoundException){
            result.setCode(7000);
            result.setMessage("ユーザーのデータが存在しません");
        }
        return result;
    }

}

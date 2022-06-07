package com.atzzazz.store.controller;

import com.atzzazz.store.service.ex.InsertException;
import com.atzzazz.store.service.ex.ServiceException;
import com.atzzazz.store.service.ex.UsernameDuplicateException;
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
            result.setMessage("サーバー側に未知のエラーが発生しています");
        }else if (e instanceof InsertException){
            result.setCode(5000);
            result.setMessage("サーバー側に未知のエラーが発生しています");
        }
        return result;
    }

}

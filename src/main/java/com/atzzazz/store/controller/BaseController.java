package com.atzzazz.store.controller;

import com.atzzazz.store.controller.ex.*;
import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.ex.*;
import com.atzzazz.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * Base　controller
 */
public class BaseController {
    //操作が成功した際のステータスコード
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setCode(4000);
            result.setMessage("ユーザー名が重複されています");
        } else if (e instanceof PasswordNotMatchException) {
            result.setCode(4001);
            result.setMessage("パースワードが間違っています");
        } else if (e instanceof UserNotFoundException) {
            result.setCode(4002);
            result.setMessage("ユーザーのデータが存在しません");
        } else if (e instanceof InsertException) {
            result.setCode(5000);
            result.setMessage("サーバー側に未知のエラーが発生しています");
        } else if (e instanceof UpdateException) {
            result.setCode(5000);
            result.setMessage("サーバー側に未知のエラーが発生しています");
        } else if (e instanceof FileEmptyException) {
            result.setCode(6000);
            result.setMessage("ファイルを選択してください");
        } else if (e instanceof FileSizeException) {
            result.setCode(6001);
            result.setMessage("アップロードするファイルのサイズを超えています");
        } else if (e instanceof FileTypeException) {
            result.setCode(6002);
            result.setMessage("ファイルのタイプが間違っています");
        } else if (e instanceof FileStateException) {
            result.setCode(6003);
            result.setMessage("アップロードするファイルは開いている状態です");
        } else if (e instanceof FileUploadIOException) {
            result.setCode(6004);
            result.setMessage("ファイルをアップロードする際にエラーが起こっています。もう一度試してください");
        }
        return result;
    }

    protected final User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    protected final Boolean userIsLogin(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

}

package com.atzzazz.store.controller;

import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.IUserService;
import com.atzzazz.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("register")
    public JsonResult<Void> register(User user) {
        userService.register(user);
        JsonResult<Void> result = new JsonResult<>(OK);
        result.setMessage("ユーザー登録成功しました");
        return result;
    }
}

package com.atzzazz.store.controller;

import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.IUserService;
import com.atzzazz.store.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


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

    @RequestMapping("login")
    public JsonResult<User> login(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  HttpSession session){
        User user = userService.login(username, password);
        JsonResult<User> userJsonResult = new JsonResult<>();
        userJsonResult.setCode(OK);
        userJsonResult.setData(user);
        userJsonResult.setMessage("ログインできました");

        session.setAttribute("user",user);

        return userJsonResult;
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(@RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword,
                                           HttpSession session){

        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        String userName = user.getUserName();

        System.out.println(userId);

        userService.changePassword(userId,userName,oldPassword,newPassword);

        return new JsonResult<>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUserId(HttpSession session){
        User sessionUser = (User) session.getAttribute("user");
        Integer userId = sessionUser.getUserId();
        User data = userService.getUserByUserId(userId);

        return new JsonResult<>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        User sessionUser = (User) session.getAttribute("user");
        Integer userId = sessionUser.getUserId();
        // 调用业务对象执行修改用户资料
        userService.changeUserInfo(userId,user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }
}

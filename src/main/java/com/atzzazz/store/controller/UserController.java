package com.atzzazz.store.controller;

import com.atzzazz.store.controller.ex.*;
import com.atzzazz.store.pojo.User;
import com.atzzazz.store.service.IUserService;
import com.atzzazz.store.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    //アップロードするファイルのマックスサイズ
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //アップロードできるファイルのタイプ
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

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
                                  HttpSession session) {
        User user = userService.login(username, password);
        JsonResult<User> userJsonResult = new JsonResult<>();
        userJsonResult.setCode(OK);
        userJsonResult.setData(user);
        userJsonResult.setMessage("ログインできました");

        session.setAttribute("user", user);

        System.out.println(user);

        return userJsonResult;
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(@RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword,
                                           HttpSession session) {

        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        String userName = user.getUserName();

        System.out.println(userId);

        userService.changePassword(userId, userName, oldPassword, newPassword);

        return new JsonResult<>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUserId(HttpSession session) {
        User sessionUser = getUserFromSession(session);
        Integer userId = sessionUser.getUserId();
        User data = userService.getUserByUserId(userId);

        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        User sessionUser = (User) session.getAttribute("user");
        Integer userId = sessionUser.getUserId();
        // 调用业务对象执行修改用户资料
        userService.changeUserInfo(userId, user);
        // 响应成功
        return new JsonResult<Void>(OK);
    }


    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
                                           HttpSession session) {
        // 判断上传的文件是否为空
        // 是：抛出异常
        if (file.isEmpty()) {
            throw new FileEmptyException("アップロードするファイルを選択してください");
        }

        // 判断上传的文件大小是否超出限制值
        // 是：抛出异常
        if (file.getSize() >= AVATAR_MAX_SIZE) {
            throw new FileSizeException("アップロードするファイルのサイズは" + (AVATAR_MAX_SIZE / 1024) + "KBを超える画像を超えないです");
        }

        // 判断上传的文件类型是否超出限制
        // 是：抛出异常
        if (!AVATAR_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("支持しないファイルです");
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        if (index > 0) {
            suffix = originalFilename.substring(index);
        }

        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e){
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        User userFromSession = getUserFromSession(session);
        Integer userId = userFromSession.getUserId();
        String userName = userFromSession.getUserName();

        // 将头像写入到数据库中
        userService.changeAvatar(userId,userName,avatar);

        // 返回成功和头像路径
        return new JsonResult<>(OK,avatar);
    }
}

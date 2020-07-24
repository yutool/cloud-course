package com.anko.coursems.service;

import com.anko.coursems.entity.User;
import com.anko.coursems.model.LoginForm;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /**
     * 按账号获取用户
     * @param account 邮箱 or 手机号
     */
    User findUserByAccount(String account);

    /**
     * 用户注册
     */
    User register(User user);

    /**
     * 忘记密码
     */
    int resetPassword(User user);

    /**
     * 更新用户信息
     */
    User updateUserInfo(User user);

    /**
     * 更换头像
     * @param id 用户id
     */
    User updateAvatar(String id, MultipartFile file);

    /**
     * 绑定邮箱
     */
    User bindEmail(User user);

    /**
     * 绑定手机
     */
    User bindPhone(User user);

    /**
     * 修改密码
     */
    int updatePassword(User user);

    /**
     * 登录，并非真实登录接口
     */
    User login(LoginForm loginForm);
}

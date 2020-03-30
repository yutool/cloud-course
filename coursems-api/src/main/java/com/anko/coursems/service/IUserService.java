package com.anko.coursems.service;

import com.anko.coursems.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

    /**
     * 按账号获取用户
     * @param account 邮箱 or 手机号
     */
    User findUserByAccount(String account);

    /**
     * 获取用户
     * @param id 用户id
     */
    User findUserById(String id);

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
}

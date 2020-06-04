package com.anko.coursems.service.impl;

import com.anko.coursems.common.exception.ServiceException;
import com.anko.coursems.common.utils.FileUtils;
import com.anko.coursems.common.utils.PasswordHelper;
import com.anko.coursems.dao.UserMapper;
import com.anko.coursems.entity.User;
import com.anko.coursems.model.LoginForm;
import com.anko.coursems.service.IUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    @Override
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User register(User user) {
        PasswordHelper.encryptPassword(user);
        user.setUserId(RandomStringUtils.randomAlphanumeric(16));
        user.setAvatar("/public/avatar_default.jpeg");
        user.setNickname(user.getUserName());
        user.setRole("user");
        userMapper.register(user);
        return user;
    }

    @Override
    public int resetPassword(User user) {
        PasswordHelper.encryptPassword(user);
        return userMapper.updatePassword(user);
    }

    @Override
    public User updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
        return userMapper.findUserById(user.getUserId());
    }

    @Override
    public User updateAvatar(String id, MultipartFile file) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "avatar_" + format.format(new Date()) + ".jpeg";
        String path = FileUtils.storeFile(file, fileName, FileUtils.getUserPath(id));
        userMapper.updateAvatar(id, path);
        return userMapper.findUserById(id);
    }

    @Override
    public User bindEmail(User user) {
        userMapper.bindEmail(user);
        return userMapper.findUserById(user.getUserId());
    }

    @Override
    public User bindPhone(User user) {
        if(userMapper.phoneIsExist(user.getPhoneNum()) != null) {
            throw new ServiceException("手机号码已被注册");
        }
        userMapper.bindPhone(user);
        return userMapper.findUserById(user.getUserId());
    }

    @Override
    public int updatePassword(User user) {
        PasswordHelper.encryptPassword(user);
        return userMapper.updatePassword(user);
    }

    @Override
    public User login(LoginForm loginForm) {
        return userMapper.login(loginForm.getAccount(), loginForm.getPassword());
    }
}

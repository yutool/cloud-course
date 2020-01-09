package com.anko.coursems.service.impl;

import com.anko.coursems.dao.AccountMapper;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.model.RequestResult;
import com.anko.coursems.model.StatusCode;
import com.anko.coursems.model.UserCourse;
import com.anko.coursems.service.IAccountService;
import com.anko.coursems.utils.JwtUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;
    // 登录
    public RequestResult login(UserInfo loginForm) {
        UserInfo userInfo = accountMapper.login(loginForm);
        if(userInfo != null) {
            String token = JwtUtil.getToken(userInfo, 0);
            return new RequestResult(userInfo, token, "登录成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("用户不存在或密码错误", StatusCode.ERROR);
        }
    }
    // 注册
    public RequestResult register(UserInfo userInfo) {
        if(accountMapper.emailIsExist(userInfo.getEmail()) != null) {
            return new RequestResult("邮箱已被使用", StatusCode.ERROR);
        }
        String userId = "u"+RandomStringUtils.randomAlphanumeric(19);
        userInfo.setUserId(userId); // 设置前验证id是否存在
        int res = accountMapper.register(userInfo);
        if(res >= 1) {
            String token = JwtUtil.getToken(userInfo, 0);
            return new RequestResult(userInfo, token,"注册成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("注册失败", StatusCode.ERROR);
        }
    }
    // 更新用户信息
    public RequestResult updateUserInfo(UserInfo userInfo) {
        int res = accountMapper.updateUserInfo(userInfo);
        if(res >= 1) {
            return new RequestResult("更新成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("更新失败", StatusCode.ERROR);
        }
    }
    // 重置密码
    public RequestResult resetPassWord(UserInfo userInfo) {
        int res = accountMapper.resetPassWord(userInfo);
        if(res >= 1) {
            return new RequestResult("重置密码成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("邮箱或密码错误", StatusCode.ERROR);
        }
    }
    // 绑定邮箱
    public RequestResult bindEmail(UserInfo userInfo) {
        if(accountMapper.emailIsExist(userInfo.getEmail()) != null) {
            return new RequestResult("邮箱已被使用", StatusCode.ERROR);
        }
        int res = accountMapper.bindEmail(userInfo);
        if(res >= 1) {
            return new RequestResult("绑定邮箱成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("绑定已存在", StatusCode.ERROR);
        }
    }
    // 绑定手机
    public RequestResult bindPhone(UserInfo userInfo) {
        if(accountMapper.phoneIsExist(userInfo.getPhoneNum()) != null) {
            return new RequestResult("手机号已被使用", StatusCode.ERROR);
        }
        int res = accountMapper.bindPhone(userInfo);
        if(res >= 1) {
            return new RequestResult("绑定手机成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("绑定手机失败，请重试", StatusCode.ERROR);
        }
    }
}

package com.anko.coursems.service.impl;

import com.anko.coursems.common.result.Result;
import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.dao.AccountMapper;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.service.IAccountService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    // 登录
    @Override
    public UserInfo login(UserInfo loginForm) {
        return accountMapper.login(loginForm);
    }

    // 注册
    @Override
    public UserInfo register(UserInfo registerForm) {
        String userId = "u"+ RandomStringUtils.randomAlphanumeric(19);
        registerForm.setUserId(userId);
        return accountMapper.register(registerForm);
    }

    @Override
    public ResultCode resetPassword(UserInfo passwordForm) {
        int res = accountMapper.resetPassword(passwordForm);
        if(res < 1) {
            return ResultCode.USER_UPDATE_ERROR;
        }
        return ResultCode.SUCCESS;
    }
}

package com.anko.coursems.service;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.model.LoginForm;

public interface IAccountService {
    UserInfo login(LoginForm loginForm);

    UserInfo register(UserInfo registerForm);

    ResultCode resetPassword(UserInfo passwordForm);
}

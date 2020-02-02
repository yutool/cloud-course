package com.anko.coursems.service;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.UserInfo;

public interface IAccountService {
    UserInfo login(UserInfo loginForm);

    UserInfo register(UserInfo registerForm);

    ResultCode resetPassword(UserInfo passwordForm);
}

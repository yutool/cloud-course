package com.anko.coursems.dao;

import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.model.LoginForm;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    UserInfo login(LoginForm loginForm);

    UserInfo register(UserInfo registerForm);

    int resetPassword(UserInfo passwordForm);
}

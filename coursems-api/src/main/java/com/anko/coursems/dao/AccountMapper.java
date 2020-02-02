package com.anko.coursems.dao;

import com.anko.coursems.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    UserInfo login(UserInfo userInfo);

    UserInfo register(UserInfo registerForm);

    int resetPassword(UserInfo passwordForm);
}

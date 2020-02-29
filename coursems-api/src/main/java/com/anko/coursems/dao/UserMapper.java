package com.anko.coursems.dao;

import com.anko.coursems.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int register(User registerForm);

    User findUserById(String userId);

    int updateUserInfo(User user);

    int updateAvatar(@Param("userId") String id, @Param("avatar") String path);

    int bindEmail(User user);

    int bindPhone(User user);

    int updatePassword(User user);

    User emailIsExist(String email);

    User phoneIsExist(String phoneNum);

    User findUserByAccount(String account);
}

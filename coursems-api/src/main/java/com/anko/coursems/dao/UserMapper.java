package com.anko.coursems.dao;

import com.anko.coursems.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    // 查找用户
    UserInfo findUserById(String userId);
    // 更新用户信息
    int updateUserInfo(UserInfo userInfo);
    // 更新头像
    int updateAvatar(@Param("userId") String id, @Param("avatar") String path);
    // 绑定邮箱
    int bindEmail(@Param("userId") String id, @Param("email") String email);
    // 绑定手机号码
    int bindPhone(@Param("userId") String id, @Param("phoneNum") String email);
    // 重置密码
    int updatePassword(UserInfo userInfo);
    // 判断邮箱是否存在
    UserInfo emailIsExist(String email);
    // 判断手机号是否被使用
    UserInfo phoneIsExist(String phoneNum);
}

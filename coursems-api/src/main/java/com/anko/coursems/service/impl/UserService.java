package com.anko.coursems.service.impl;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.dao.UserMapper;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    // 更新用户信息
    public ResultCode updateUserInfo(UserInfo userInfo) {
        int res = userMapper.updateUserInfo(userInfo);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
    // 重置密码
    public ResultCode updatePassword(UserInfo userInfo) {
        int res = userMapper.updatePassword(userInfo);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
    // 绑定邮箱
    public ResultCode bindEmail(UserInfo userInfo) {
        if(userMapper.emailIsExist(userInfo.getEmail()) != null) {
            return ResultCode.DATA_ALREADY_EXISTED;
        }
        int res = userMapper.bindEmail(userInfo);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
    // 绑定手机
    public ResultCode bindPhone(UserInfo userInfo) {
        if(userMapper.phoneIsExist(userInfo.getPhoneNum()) != null) {
            return ResultCode.DATA_ALREADY_EXISTED;
        }
        int res = userMapper.bindPhone(userInfo);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
}

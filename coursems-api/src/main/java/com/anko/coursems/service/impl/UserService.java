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

    // 修改头像
    public ResultCode updateAvatar(String id, String path) {
        userMapper.updateAvatar(id, path);
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
    public ResultCode bindEmail(String id, String email) {
        if(userMapper.emailIsExist(email) != null) {
            return ResultCode.DATA_ALREADY_EXISTED;
        }
        int res = userMapper.bindEmail(id, email);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }

    // 绑定手机
    public ResultCode bindPhone(String id, String phone) {
        if(userMapper.phoneIsExist(phone) != null) {
            return ResultCode.DATA_ALREADY_EXISTED;
        }
        int res = userMapper.bindPhone(id, phone);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }
}

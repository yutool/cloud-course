package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PutMapping
    public Result updateUserInfo(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        ResultCode code = userService.updateUserInfo(userInfo);
        return Result.builder().build().setResultCode(code);
    }

    @PutMapping("/password")
    public Result updatePassword(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        ResultCode code = userService.updatePassword(userInfo);
        return Result.builder().build().setResultCode(code);
    }

    @PutMapping("/email")
    public Result bindEmail(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        ResultCode code = userService.bindEmail(userInfo);
        return Result.builder().build().setResultCode(code);
    }

    @PutMapping("/phone")
    public Result bindPhone(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        ResultCode code = userService.bindPhone(userInfo);
        return Result.builder().build().setResultCode(code);
    }
}

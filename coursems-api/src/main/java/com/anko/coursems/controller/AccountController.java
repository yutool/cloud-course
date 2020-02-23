package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.LoginForm;
import com.anko.coursems.model.UserDto;
import com.anko.coursems.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@SessionAttributes(value = "userInfo", types = {UserInfo.class})
@RequestMapping("/api/v1")
@RestController
public class AccountController {
    @Autowired
    private IAccountService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, ModelMap model) {
        log.info("用户登录: " + loginForm);
        UserInfo user = userService.login(loginForm);
        if(user == null) {
            return Result.error(ResultCode.USER_LOGIN_ERROR);
        }
        model.put("userInfo", user);
        return Result.success(UserDto.builder().build().convertFor(user));
    }

    @PostMapping("/logout")
    public Result logout(SessionStatus sessionStatus) {
        log.info("用户退出");
        sessionStatus.setComplete();
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserInfo registerForm) {
        log.info("用户注册: " + registerForm);
        UserInfo userInfo = userService.register(registerForm);
        if(userInfo == null) {
            return Result.error(ResultCode.USER_REGISTER_ERROR);
        }
        return Result.success(userInfo);
    }

    @PostMapping("/reset-password")
    public Result resetPassword(@RequestBody UserInfo form) {
        log.info("忘记密码: " + form);
        ResultCode code =  userService.resetPassword(form);
        return Result.builder().build().setResultCode(code);
    }

}

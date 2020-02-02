package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class AccountController {
    @Autowired
    private IAccountService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserInfo loginForm, HttpServletRequest request) {
        log.info(loginForm.toString());
        UserInfo userInfo = userService.login(loginForm);
        if(userInfo == null) {
            return Result.error(ResultCode.USER_LOGIN_ERROR);
        }
        request.getSession().setAttribute("userInfo", userInfo);
        return Result.success(userInfo);
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        log.info("用户退出");
        request.getSession().removeAttribute("userInfo");
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserInfo registerForm) {
        log.info(registerForm.toString());
        UserInfo userInfo = userService.register(registerForm);
        if(userInfo == null) {
            return Result.error(ResultCode.USER_REGISTER_ERROR);
        }
        return Result.success(userInfo);
    }

    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody UserInfo passwordForm) {
        log.info(passwordForm.toString());
        ResultCode code =  userService.resetPassword(passwordForm);
        return Result.builder().build().setResultCode(code);
    }

}

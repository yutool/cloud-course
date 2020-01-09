package com.anko.coursems.controller;

import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.model.RequestResult;
import com.anko.coursems.service.impl.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public RequestResult login(@RequestBody UserInfo loginForm) {
        log.info(loginForm.toString());
        return accountService.login(loginForm);
    }

    @PostMapping("/register")
    public RequestResult register(@RequestBody UserInfo registerForm) {
        log.info(registerForm.toString());
        return accountService.register(registerForm);
    }

    @PutMapping("resetPassWord")
    public RequestResult resetPassWord(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        return accountService.resetPassWord(userInfo);
    }

    @PutMapping("userInfo")
    public RequestResult updateUserInfo(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        return accountService.updateUserInfo(userInfo);
    }

    @PutMapping("bindEmail")
    public RequestResult bindEmail(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        return accountService.bindEmail(userInfo);
    }

    @PutMapping("bindPhone")
    public RequestResult bindPhone(@RequestBody UserInfo userInfo) {
        log.info(userInfo.toString());
        return accountService.bindPhone(userInfo);
    }
}

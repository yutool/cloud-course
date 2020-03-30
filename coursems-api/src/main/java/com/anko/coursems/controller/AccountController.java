package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.common.utils.UserUtils;
import com.anko.coursems.entity.User;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.LoginForm;
import com.anko.coursems.model.RegisterForm;
import com.anko.coursems.model.UserDto;
import com.anko.coursems.service.IUserService;
import com.anko.coursems.service.impl.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api(tags = "帐号管理")
@RequestMapping("/api/v1")
@RestController
@Slf4j
public class AccountController {
    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "用户登录")
    @LogAnnotation(operation = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        return executeLogin(loginForm);
    }

    @ApiOperation(value = "游客登录")
    @LogAnnotation(operation = "游客登录")
    @PostMapping("/login/visitor")
    public Result visitorsLogin() {
        LoginForm loginForm = new LoginForm();
        loginForm.setAccount("visitor@qq.com");
        loginForm.setPassword("123456");
        return executeLogin(loginForm);
    }

    private Result executeLogin(LoginForm loginForm) {
        Result r = Result.builder().build();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getAccount(), loginForm.getPassword());
        try {
            subject.login(token);
            User currentUser = userService.findUserByAccount(loginForm.getAccount());
            UserUtils.setCurrentUser(currentUser);
            r.setResultCode(ResultCode.SUCCESS).setData(UserDto.builder().build().convertFor(currentUser));
        } catch (UnknownAccountException e) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
        } catch (LockedAccountException e) {
            r.setResultCode(ResultCode.USER_ACCOUNT_FORBIDDEN);
        } catch (AuthenticationException e) {
            r.setResultCode(ResultCode.USER_LOGIN_ERROR);
        } catch (Exception e) {
            r.setResultCode(ResultCode.ERROR);
        }
        return r;
    }

    @ApiOperation(value = "注册账号")
    @LogAnnotation(operation = "注册账号")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterForm form) {
        String verityCode =  stringRedisTemplate.opsForValue().get(form.getEmail());
        if(!form.getVerifyCode().equals(verityCode)) {
            return Result.error(ResultCode.EMAIL_ERROR);
        }
        User user = userService.register(form.convertToUser());
        return Result.success(UserDto.builder().build().convertFor(user));
    }

    @ApiOperation(value = "忘记密码")
    @LogAnnotation(operation = "忘记密码")
    @PutMapping("/reset-password")
    public Result resetPassword(@RequestBody RegisterForm form) {
        String verityCode =  stringRedisTemplate.opsForValue().get(form.getEmail());
        if(!form.getVerifyCode().equals(verityCode)) {
            return Result.error(ResultCode.EMAIL_ERROR);
        }
        userService.resetPassword(form.convertToUser());
        return Result.success();
    }

}

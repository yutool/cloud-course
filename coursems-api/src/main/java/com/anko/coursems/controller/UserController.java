package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.common.util.UserUtils;
import com.anko.coursems.common.support.BaseController;
import com.anko.coursems.entity.User;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.RegisterForm;
import com.anko.coursems.model.UserDTO;
import com.anko.coursems.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "用户模块")
@RequestMapping("/api/v1/users")
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "获取当前用户")
    @GetMapping("/current")
    public Result getCurrentUser() {
        User user = UserUtils.getCurrentUser();
        return Result.success(UserDTO.builder().build().convertFor(user));
    }

    @RequiresRoles(value={"user","admin"},logical= Logical.OR)
    @ApiOperation(value = "更新用户信息")
    @LogAnnotation(operation = "更新用户信息")
    @PutMapping
    public Result updateUserInfo(@RequestBody User form) {
        if(form.getUserId() == null) {
            return Result.error(ResultCode.PARAM_IS_INVALID);
        }
        User user = userService.updateUserInfo(form);
        UserUtils.setCurrentUser(user);
        return Result.success(UserDTO.builder().build().convertFor(user));
    }

    @RequiresRoles(value={"user","admin"},logical=Logical.OR)
    @ApiOperation(value = "更新用户头像")
    @LogAnnotation(operation = "更新用户头像")
    @PostMapping("/avatar/{id}")
    public Result updateAvatar(@PathVariable String id, @RequestParam("file") MultipartFile file) {
//        if(FileContentTypeUtils.isImage(file.getContentType())) {
//            return Result.error(ResultCode.PARAM_IS_INVALID);
//        }
        User user = userService.updateAvatar(id, file);
        UserUtils.setCurrentUser(user);
        return Result.success(UserDTO.builder().build().convertFor(user));
    }

    @RequiresRoles(value={"user","admin"},logical=Logical.OR)
    @ApiOperation(value = "绑定邮箱")
    @LogAnnotation(operation = "绑定邮箱")
    @PutMapping("/email")
    public Result bindEmail(@RequestBody RegisterForm form) {
        String verifyCode = stringRedisTemplate.opsForValue().get(form.getEmail());
        if(!form.getVerifyCode().equals(verifyCode)) {
            return Result.error(ResultCode.VERIFY_CODE_ERROR);
        }
        User user = userService.bindEmail(form.convertToUser());
        UserUtils.setCurrentUser(user);
        return Result.success(UserDTO.builder().build().convertFor(user));
    }

    @RequiresRoles(value={"user","admin"},logical=Logical.OR)
    @ApiOperation(value = "绑定手机")
    @LogAnnotation(operation = "绑定手机")
    @PutMapping("/phone")
    public Result bindPhone(@RequestBody User form) {
        if(form.getUserId() == null) {
            return Result.error(ResultCode.PARAM_IS_INVALID);
        }
        User user = userService.bindPhone(form);
        UserUtils.setCurrentUser(user);
        return Result.success(UserDTO.builder().build().convertFor(user));
    }

    @RequiresRoles(value={"user","admin"},logical=Logical.OR)
    @ApiOperation(value = "修改密码")
    @LogAnnotation(operation = "修改密码")
    @PutMapping("/password")
    public Result updatePassword(@RequestBody User form) {
        return handleResult(userService.updatePassword(form));
    }
}

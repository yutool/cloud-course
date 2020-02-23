package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.common.utils.FileUploadUtils;
import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.UserDto;
import com.anko.coursems.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@SessionAttributes(value = "userInfo", types = {UserInfo.class})
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public Result getCurrentUser(@SessionAttribute("userInfo") UserInfo user) {
        log.info("当前用户：" + user);
        return Result.success(UserDto.builder().build().convertFor(user));
    }

    @PutMapping
    public Result updateUserInfo(@SessionAttribute("userInfo") UserInfo user, @RequestBody UserInfo userInfo, ModelMap model) {
        log.info("更新用户信息: " + userInfo);
        ResultCode code = userService.updateUserInfo(userInfo);
        user.setUserName(userInfo.getUserName());
        user.setNickname(userInfo.getNickname());
        user.setStuNum(userInfo.getStuNum());
        user.setGender(userInfo.getGender());
        user.setBirthday(userInfo.getBirthday());
        user.setSignature(userInfo.getSignature());
        model.put("userInfo", user);
        return Result.success(UserDto.builder().build().convertFor(user));
    }

    @PostMapping("/avatar/{id}")
    public Result updateAvatar(
            @SessionAttribute("userInfo") UserInfo user, @PathVariable String id,
            @RequestParam("file") MultipartFile file, ModelMap model) {
        log.info("更新头像: " + file.getOriginalFilename());
        String fileName = FileUploadUtils.storeFile(file, FileUploadUtils.STORE_AVATAR);
        final String relativePath = FileUploadUtils.USER_AVATAR_PR + fileName;
        userService.updateAvatar(id, relativePath);
        user.setAvatar(relativePath);
        model.put("userInfo", user);
        return Result.success(UserDto.builder().build().convertFor(user));
    }

    @PutMapping("/email/{id}/{email}")
    public Result bindEmail(@PathVariable String id, @PathVariable String email,
                            @SessionAttribute("userInfo") UserInfo user, ModelMap model) {
        log.info("修改邮箱: " + email);
        ResultCode code = userService.bindEmail(id, email);
        user.setEmail(email);
        model.put("userInfo", user);
        return Result.success(user);
    }

    @PutMapping("/phone/{id}/{phone}")
    public Result bindPhone(@PathVariable String id, @PathVariable String phone,
                            @SessionAttribute("userInfo") UserInfo user, ModelMap model) {
        log.info("修改手机: " + phone);
        ResultCode code = userService.bindPhone(id, phone);
        user.setPhoneNum(phone);
        model.put("userInfo", user);
        return Result.success(user);
    }

    @PutMapping("/password")
    public Result updatePassword(@RequestBody UserInfo userInfo) {
        log.info("修改密码: " + userInfo);
        ResultCode code = userService.updatePassword(userInfo);
        return Result.builder().build().setResultCode(code);
    }
}

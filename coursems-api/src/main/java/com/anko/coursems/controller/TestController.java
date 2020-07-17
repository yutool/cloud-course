package com.anko.coursems.controller;

import com.anko.coursems.common.result.Result;
import com.anko.coursems.dao.UserMapper;
import com.anko.coursems.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/test/1")
    public Result test1() {
        return Result.success(userMapper.selectById("0ay8PBdE0m6dkuJP"));
    }

    @GetMapping("/api/test/2")
    public Result test2() {
        User user = new User();
        user.setRole("user");
        user.setUserName("user");
        return Result.success(userMapper.selectList(user));
    }

    @GetMapping("/api/test/3")
    public Result test3() {
        return Result.success(userMapper.selectAll());
    }

    @GetMapping("/api/test/insert")
    public Result insert() {
        User user = new User();
        user.setUserId("11");
        user.setUserName("22");
        userMapper.insert(user);
        return Result.success();
    }

    @GetMapping("/api/test/update")
    public Result update() {
        User user = new User();
        user.setUserId("11");
        user.setEmail("email");
        user.setUserName("22");
        userMapper.updateById(user);
        return Result.success();
    }

    @GetMapping("/api/test/delete")
    public Result delete() {
        userMapper.deleteById("1");
        return Result.success();
    }


}

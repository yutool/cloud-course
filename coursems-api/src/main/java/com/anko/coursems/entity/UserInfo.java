package com.anko.coursems.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@ToString(exclude = {"signature","birthday", "gender", "nickname"})
@Data
public class UserInfo {
    private String userId;
    private String userName;
    private String email;
    private String userPwd;
    private String nickname;
    private String stuNum;
    private String gender;
    private Date birthday;
    private String phoneNum;
    private String signature;
}
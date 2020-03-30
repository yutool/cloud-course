package com.anko.coursems.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString(exclude = {"signature", "gender"})
@Data
public class User implements Serializable {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String avatar;
    private String nickname;
    private String gender;
    private String stuNum;
    private String phoneNum;
    private Date birthday;
    private String signature;
    private String salt;
    private String role;
}
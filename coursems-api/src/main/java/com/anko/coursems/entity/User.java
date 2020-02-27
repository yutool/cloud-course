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
    private String stuNum;
    private String gender;
    private Date birthday;
    private String phoneNum;
    private String signature;
    private String role;
}
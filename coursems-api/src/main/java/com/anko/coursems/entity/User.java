package com.anko.coursems.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@ToString(exclude = {"signature", "gender"})
@Data
@ApiModel("用户实体")
public class User implements Serializable {
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("邮箱")
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
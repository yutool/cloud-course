package com.ankoye.coursems.entity;

import com.ankoye.coursems.common.support.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ankoye@qq.com
 */
@Data
@Accessors(chain = true)
@ToString(exclude = {"signature", "gender"})
@ApiModel("用户实体")
public class User extends BaseEntity {

//    private static final long serialVersionUID = -2068478369226122037L;

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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    private String signature;

    private String salt;

    private String role;
}
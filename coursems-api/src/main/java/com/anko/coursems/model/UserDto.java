package com.anko.coursems.model;

import com.anko.coursems.entity.UserInfo;
import com.google.common.base.Converter;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Data
@Builder
public class UserDto {
    private String userId;
    private String userName;
    private String email;
    private String avatar;
    private String nickname;
    private String stuNum;
    private String gender;
    private Date birthday;
    private String phoneNum;
    private String signature;

    // 获取头像时，需要获取服务器绝对地址
    public String getAvatar() {
        if(avatar.startsWith("http"))
            return avatar;
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(avatar).toUriString();
    }

    public UserInfo convertToUser(){
        UserConverter userConverter = new UserConverter();
        UserInfo user = userConverter.convert(this);
        return user;
    }

    public UserDto convertFor(UserInfo user){
        UserConverter userConverter = new UserConverter();
        UserDto userDto = userConverter.reverse().convert(user);
        return userDto;
    }


    private static class UserConverter extends Converter<UserDto, UserInfo> {
        @Override
        protected UserInfo doForward(UserDto userDto) {
            UserInfo user = new UserInfo();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(userDto, user);
            return user;
        }

        @Override
        protected UserDto doBackward(UserInfo user) {
            UserDto userDto = UserDto.builder().build();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }

    }
}

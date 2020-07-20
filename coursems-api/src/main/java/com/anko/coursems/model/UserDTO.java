package com.anko.coursems.model;

import com.anko.coursems.entity.User;
import com.google.common.base.Converter;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Data
@Builder
public class UserDTO {
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

    public User convertToUser(){
        UserConverter userConverter = new UserConverter();
        User user = userConverter.convert(this);
        return user;
    }

    public UserDTO convertFor(User user){
        UserConverter userConverter = new UserConverter();
        UserDTO userDto = userConverter.reverse().convert(user);
        return userDto;
    }


    private static class UserConverter extends Converter<UserDTO, User> {
        @Override
        protected User doForward(UserDTO userDto) {
            User user = new User();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(userDto, user);
            return user;
        }

        @Override
        protected UserDTO doBackward(User user) {
            UserDTO userDto = UserDTO.builder().build();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }

    }
}

package com.anko.coursems.model;

import com.anko.coursems.entity.User;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RegisterForm {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String verifyCode;

    public User convertToUser(){
        UserConverter userConverter = new UserConverter();
        return userConverter.convert(this);
    }

    private static class UserConverter extends Converter<RegisterForm, User> {
        @Override
        protected User doForward(RegisterForm form) {
            User user = new User();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(form, user);
            return user;
        }

        @Override
        protected RegisterForm doBackward(User user) {
            throw new ClassCastException("User can't be converted to RegisterForm");
        }

    }
}

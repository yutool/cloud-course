package com.anko.coursems.model;

import com.anko.coursems.entity.User;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegisterForm {
    private String userId;

    @NotNull
    private String userName;

    @Email
    private String email;

    @NotNull
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

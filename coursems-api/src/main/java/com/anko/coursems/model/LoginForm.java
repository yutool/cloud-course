package com.anko.coursems.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginForm implements Serializable {
    private String account;

    private String password;
}

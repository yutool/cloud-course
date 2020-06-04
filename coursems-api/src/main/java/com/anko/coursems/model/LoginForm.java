package com.anko.coursems.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginForm implements Serializable {
    @NotNull
    private String account;

    @NotNull
    private String password;
}

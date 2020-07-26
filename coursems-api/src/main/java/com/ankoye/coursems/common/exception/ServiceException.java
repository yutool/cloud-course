package com.ankoye.coursems.common.exception;

import lombok.Getter;

public class ServiceException extends RuntimeException  {
    @Getter
    private Integer code;
    @Getter
    private String message;

    public ServiceException (String message) {
        this(1, message);
    }

    public ServiceException (Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

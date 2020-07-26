package com.ankoye.coursems.common.exception;

import lombok.Getter;

public class LimitIPRequestException extends RuntimeException  {
    @Getter
    private Integer code;
    @Getter
    private String message;

    public LimitIPRequestException (String message) {
        this(1, message);
    }

    public LimitIPRequestException (Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}


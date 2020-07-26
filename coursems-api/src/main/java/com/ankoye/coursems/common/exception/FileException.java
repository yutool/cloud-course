package com.ankoye.coursems.common.exception;

import lombok.Getter;

public class FileException extends RuntimeException{
    @Getter
    private Integer code;
    @Getter
    private String message;

    public FileException(String message) {
        super(message);
        this.code = 1;
        this.message = message;
    }


    public FileException(String message, Throwable cause) {
        super(message, cause);
        this.code = 1;
        this.message = message;
    }
}
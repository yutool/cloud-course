package com.anko.coursems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResult {
    private Object content;     // 内容
    private String token;
    private String message;     // 提示消息
    private int status;         // 状态码

    public RequestResult(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public RequestResult(Object content, String message, int status) {
        this.content = content;
        this.message = message;
        this.status = status;
    }

}

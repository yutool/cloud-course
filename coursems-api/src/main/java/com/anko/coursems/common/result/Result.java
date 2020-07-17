package com.anko.coursems.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * api接口数据返回封装
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = -4762928619495260423L;

    private Integer code;

    private String message;

    private Object data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error() {
        return error(ResultCode.ERROR);
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result error(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public Result setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
        return this;
    }

    public Map<String, Object> simple() {
        Map<String, Object> simple = new HashMap<>();
        this.data = simple;
        return simple;
    }

}

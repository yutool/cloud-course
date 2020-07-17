package com.anko.coursems.core;

import com.anko.coursems.common.result.Result;

/**
 * @author ankoye@qq.com
 */
public abstract class BaseController {

    /**
     * 根据返回值判断是否操作成功，一般使用在 增 删 改
     * 若不需要判断返回值，不要使用此方法
     * @param o service result
     * @param successMsg 成功消息提示
     * @param errorMsg 错误消息提示
     */
    protected Result handleResult(Object o, String successMsg, String errorMsg) {
        boolean flag = isFlag(o);
        Result result;
        if (flag) {
            result = Result.success(o);
            if (successMsg != null)  result.setMessage(successMsg);
        } else {
            result = Result.error();
            if (errorMsg != null)  result.setMessage(successMsg);
        }
        return result;
    }
    protected Result handleResult(Object result) {
        return handleResult(result, null, null);
    }

    private boolean isFlag(Object result) {
        boolean flag = result != null;
        if (result instanceof Integer) {
            flag = (Integer) result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean) result;
        }
        return flag;
    }
}

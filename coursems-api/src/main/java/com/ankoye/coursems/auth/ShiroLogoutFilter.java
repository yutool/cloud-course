package com.ankoye.coursems.auth;

import com.ankoye.coursems.common.result.Result;
import com.ankoye.coursems.common.util.ResultUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义登出,登出之后,清理当前用户redis部分缓存信息
 */
public class ShiroLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //登出操作 清除缓存  subject.logout() 可以自动清理缓存信息
        Subject subject = getSubject(request, response);
        subject.logout();

        Result result = Result.success();
        ResultUtils.responseWrite(httpServletResponse, result);
        return false;
    }

}
package com.anko.coursems.filter;

import com.anko.coursems.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    Logger log = LoggerFactory.getLogger(JwtFilter.class);

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        // 从Http请求获得授权
        // 客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
        final String authHeader = request.getHeader("authorization");

        // 设置不拦截的请求
        final String path = request.getServletPath();
        if (path.equals("/login") || path.equals("/register") || path.equals("/resetPassWord")) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        }
        // 如果Http请求是OPTIONS，则返回状态码200
        else if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        }
        // 排除 OPTIONS, JWT应检查其他请求
        else {
            // 检查授权，检查令牌是否由“Bearer”启动
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.error("不是由Bearer启动");
                response.setStatus(401); // 无token返回401
                return ;
            }
            // 从授权中获取JWT令牌
            final String token = authHeader.substring(7);
            try {
                // 使用JWT解析器检查签名是否有效, 如果token已经过期了，这里会抛出jwt过期异常。
                final Claims claims = JwtUtil.parseToken(token);
                // 将 claim 添加到请求标头
                request.setAttribute("claims", claims);
                chain.doFilter(req, res);
            } catch (Exception e) {
                // token过期，返回403
                // response.setStatus(403);
                response.setStatus(403);
                response.setHeader("status", "1000");
                log.error("无效token，已拦截");
            }
        }
    }
}
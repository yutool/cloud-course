package com.anko.coursems.common.aspect;

import com.anko.coursems.common.annotation.LimitIPRequest;
import com.anko.coursems.common.cache.RedisManager;
import com.anko.coursems.common.exception.LimitIPRequestException;
import com.anko.coursems.common.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class LimitIPRequestAspect {
    @Autowired
    private RedisManager redisManager;

    @Pointcut("execution(* com.anko.coursems.controller.*.*(..))")
    public void before(){
    }

    @Before("before()")
    public void requestLimit(JoinPoint joinPoint) {
        // 获取HttpRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LimitIPRequest limit = this.getAnnotation(joinPoint);
        if(limit == null) {
            return;
        }

        String ip = IpUtils.getIpAddr(request);
        String uri = request.getRequestURI();
        String redisKey = "limit-ip-request:" + uri + ":" + ip;
        Object count =  redisManager.get(redisKey);
        if(count != null && (int) count >= limit.value()) {
            throw new LimitIPRequestException("请求频繁，请售后重试");
        }
        if(count == null) {
            redisManager.set(redisKey, 1, limit.time());
        }else {
            redisManager.set(redisKey, ((int)count)+1, limit.time());
        }
    }

    /**
     * 获得注解
     */
    private LimitIPRequest getAnnotation(JoinPoint joinPoint) {
        LimitIPRequest annotation = null;
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        //获取方法上的注解
        annotation = method.getAnnotation(LimitIPRequest.class);
        if (annotation == null) {
            //获取类上的注解
            annotation = joinPoint.getTarget().getClass().getAnnotation(LimitIPRequest.class);
            if (annotation == null) {
                //获取接口上的注解
                for (Class<?> cls : joinPoint.getClass().getInterfaces()) {
                    annotation = cls.getAnnotation(LimitIPRequest.class);
                }
            }
        }
        return annotation;
    }

}
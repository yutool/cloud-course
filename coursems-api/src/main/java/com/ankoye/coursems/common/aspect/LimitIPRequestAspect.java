package com.ankoye.coursems.common.aspect;

import com.ankoye.coursems.common.annotation.LimitIPRequest;
import com.ankoye.coursems.common.exception.LimitIPRequestException;
import com.ankoye.coursems.common.util.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LimitIPRequestAspect {
    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Pointcut("execution(* com.ankoye.coursems.controller.*.*(..))")
//    @Pointcut("@annotation(com.anko.coursems.common.annotation.LimitIPRequest)")
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
        Long count = redisTemplate.opsForValue().increment(redisKey);
        if(count == 1) {
            redisTemplate.expire(redisKey, limit.time(), TimeUnit.SECONDS);
        }
        if(count > limit.value()) {
            throw new LimitIPRequestException("请求频繁，请售后重试");
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

        // 获取方法上的注解
        annotation = method.getAnnotation(LimitIPRequest.class);
        if (annotation == null) {
            // 获取类上的注解
            annotation = joinPoint.getTarget().getClass().getAnnotation(LimitIPRequest.class);
            if (annotation == null) {
                // 获取接口上的注解
                for (Class<?> cls : joinPoint.getClass().getInterfaces()) {
                    annotation = cls.getAnnotation(LimitIPRequest.class);
                }
            }
        }
        return annotation;
    }

}
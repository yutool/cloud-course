package com.anko.coursems.common.aspect;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.constant.LogType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {
    @Autowired
    HttpServletRequest request;

    @Pointcut("@annotation(com.anko.coursems.common.annotation.LogAnnotation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        // 排除忽略的
        boolean urlLogRequired = Boolean.TRUE;
        boolean requestLogRequired = Boolean.TRUE;
        boolean responseLogRequired = Boolean.TRUE;
        for(LogType lt : logAnnotation.exclude()) {
            switch (lt) {
                case URL:
                    urlLogRequired = Boolean.FALSE;
                    break;
                case REQUEST:
                    requestLogRequired = Boolean.FALSE;
                    break;
                case RESPONSE:
                    responseLogRequired = Boolean.FALSE;
                    break;
                case ALL:
                    urlLogRequired = Boolean.FALSE;
                    requestLogRequired = Boolean.FALSE;
                    responseLogRequired = Boolean.FALSE;
                    break;
                default:
            }
        }

        //url日志
        if (urlLogRequired) {
            log.info("请求url: {}", request.getRequestURL().toString());
        }
        //请求日志
        if (requestLogRequired) {
            log.info("{} - 请求参数: {}", logAnnotation.operation(), point.getArgs());
        }
        //执行方法
        Object result = point.proceed();
        //响应日志
        if (responseLogRequired) {
            log.info("请求结果: {}", result);
        }

        //执行时长(毫秒)
        // long time = System.currentTimeMillis() - beginTime;
        //保存日志
        // saveLog(point, time);
        return result;
    }

//    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Log log = new Log();
//        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
//
//        if (log != null) {
//            log.setModule(logAnnotation.module());
//            log.setOperation(logAnnotation.operation());
//        }
//
//        //请求的方法名
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = signature.getName();
//        log.setMethod(className + "." + methodName + "()");
//
////        //请求的参数
////        Object[] args = joinPoint.getArgs();
////        String params = JSON.toJSONString(args[0]);
////        log.setParams(params);
//
//        //获取request 设置IP地址
//        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//        log.setIp(IpUtils.getIpAddr(request));
//
////        //用户名
////        User user = UserUtils.getCurrentUser();
////
////        if (null != user) {
////            log.setUserId(user.getId());
////            log.setNickname(user.getNickname());
////        } else {
////            log.setUserId(-1L);
////            log.setNickname("获取用户信息为空");
////        }
//
//        log.setTime(time);
//        log.setCreateDate(new Date());
//
//        logService.saveLog(log);
//    }

}

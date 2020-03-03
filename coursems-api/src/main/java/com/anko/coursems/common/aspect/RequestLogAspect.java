package com.anko.coursems.common.aspect;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.constant.LogType;
import com.anko.coursems.common.utils.IpUtils;
import com.anko.coursems.common.utils.JsonUtils;
import com.anko.coursems.common.utils.UserUtils;
import com.anko.coursems.dao.LogMapper;
import com.anko.coursems.entity.Log;
import com.anko.coursems.entity.User;
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
import java.util.Date;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LogMapper logMapper;

    @Pointcut("@annotation(com.anko.coursems.common.annotation.LogAnnotation)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 控制台输出
         consoleLog(point, logAnnotation, result);
        // 存储到数据库
//        saveLog(point, logAnnotation, time);
        return result;
    }

    private void consoleLog(ProceedingJoinPoint point, LogAnnotation logAnnotation, Object result) {
        // 排除忽略
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
        //响应日志
        if (responseLogRequired) {
            log.info("请求结果: {}", result);
        }

    }

    private void saveLog(ProceedingJoinPoint point, LogAnnotation logAnnotation, long time) {
        Log log = new Log();

        log.setModule(logAnnotation.module());
        log.setOperation(logAnnotation.operation());

        // 请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        log.setMethod(className + "." + methodName + "()");

        // 请求的参数
        // Object[] args = point.getArgs();
        // String params = JsonUtils.toString(args[0]);
        // log.setParams(params);

        // 获取request 设置IP地址
        log.setIp(IpUtils.getIpAddr(request));

        // 用户
        User user = UserUtils.getCurrentUser();
        if (null != user) {
            log.setUserId(user.getUserId());
            log.setUserName(user.getUserName());
        } else {
            log.setUserId("-1");
            log.setUserName("获取用户信息为空");
        }

        log.setTime(time);
        log.setCreateTime(new Date());

        logMapper.save(log);
    }

}

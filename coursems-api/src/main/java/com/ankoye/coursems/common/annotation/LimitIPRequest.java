package com.ankoye.coursems.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)        // 设置顺序为最高优先级
public @interface LimitIPRequest {

    /**
     * 限制某时间段内可以访问的次数，默认设置100
     */
    int value() default 100;

    /**
     * 限制访问的某一个时间段，单位为秒，默认值1分钟即可
     */
    int time() default 60;

}
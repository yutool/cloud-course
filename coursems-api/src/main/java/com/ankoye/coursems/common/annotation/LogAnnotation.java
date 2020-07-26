package com.ankoye.coursems.common.annotation;

import com.ankoye.coursems.common.constant.LogType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operation() default "";

    LogType[] exclude() default {};
}

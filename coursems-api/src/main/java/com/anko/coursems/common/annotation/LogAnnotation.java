package com.anko.coursems.common.annotation;

import com.anko.coursems.common.constant.LogType;
import lombok.ToString;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operation() default "";

    LogType[] exclude() default {};
}

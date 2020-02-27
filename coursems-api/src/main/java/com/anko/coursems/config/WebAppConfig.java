package com.anko.coursems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    // 配置跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .maxAge(3600);
    }

    // 配置拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/api/v1/login", "/api/v1/register", "/api/v1/forget-password")
//                .excludePathPatterns("/static/**","/webjars/**","/swagger-ui.html","/swagger-resources/**","/v2/**");
//    }

}

package com.anko.coursems.config;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RandomStringUtils randomStringUtils() {
        return new RandomStringUtils();
    }
}

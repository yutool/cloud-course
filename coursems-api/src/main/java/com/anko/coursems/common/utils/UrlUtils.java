package com.anko.coursems.common.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UrlUtils {

    public static String toServerUrl(String url) {
        if(url.startsWith("http"))
            return url;
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(url).toUriString();
    }

}

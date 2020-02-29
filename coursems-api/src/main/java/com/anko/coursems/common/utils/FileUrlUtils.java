package com.anko.coursems.common.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class FileUrlUtils {

    public static final String DOWN_LINK_PR = "files/download/";

    public static String toServerUrl(String url) {
        if(url == null) return null;
        if(isServerUrl(url)) return url;
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(url).toUriString();
    }

    public static String toDownloadUrl(String url) {
        if(url == null) return null;
        if(isServerUrl(url)) return url;
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DOWN_LINK_PR+url).toUriString();
    }

    private static boolean isServerUrl(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

}

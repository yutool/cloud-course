package com.ankoye.coursems.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object obj) {
        if(obj == null)
            return null;
        if(obj.getClass() == String.class)
            return (String) obj;
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(InputStream is, Class<T> tClass) {
        try {
            return mapper.readValue(is, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> List<T> toList(String json, Class<T> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

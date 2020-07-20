package com.anko.coursems.common.util;

import com.anko.coursems.common.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void responseWrite(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

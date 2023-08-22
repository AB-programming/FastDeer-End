package com.deer.fastdeerend.util;

import com.deer.fastdeerend.common.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义封装响应工具类
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Component
public class ResponseUtil {

    @Resource
    private ObjectMapper objectMapper;

    public void printResult(HttpServletResponse response, HttpResponse<String> result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(result));
        writer.flush();
    }

}

package com.deer.fastdeerend.config.security.handler;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 授权失败处理器
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Component
public class AuthorizationFailHandler implements AccessDeniedHandler {

    @Resource
    private ResponseUtil responseUtil;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();
        HttpResponse<String> res = builder.code(HttpResponseStatusCodeSet.Forbidden.getValue())
                .msg("没有权限").build();
        responseUtil.printResult(response, res);
    }
}

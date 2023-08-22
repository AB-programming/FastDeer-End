package com.deer.fastdeerend.config.security.handler;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.RedisUtil;
import com.deer.fastdeerend.util.ResponseUtil;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 退出登录处理器
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Component
public class FastDeerLogoutHandler implements LogoutHandler {

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private ResponseUtil responseUtil;

    @Resource
    private JWTUtil jwtUtil;

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String token = request.getHeader("Authorization");

        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();

        if (!StringUtils.hasText(token)) {
            responseUtil.printResult(response, builder.code(HttpResponseStatusCodeSet.Forbidden.getValue()).msg("缺少token").build());
            return;
        }

        VerifyTokenResult verifyTokenResult = jwtUtil.verifyToken(token);

        if (!verifyTokenResult.isStatus()) {
            responseUtil.printResult(response, builder.code(HttpResponseStatusCodeSet.Forbidden.getValue()).msg(verifyTokenResult.getData()).build());
            return;
        }

        redisUtil.drop(token);

        SecurityContextHolder.getContext().setAuthentication(null);
        HttpResponse<String> res = builder.code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("退出成功").build();
        try {
            responseUtil.printResult(response, res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

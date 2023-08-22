package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.user.UserRequest;
import com.deer.fastdeerend.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author AB-style
 * @date 2023/07/01
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public HttpResponse<String> login(@RequestBody UserRequest userRequest) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();
        if (null == userRequest) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("没有请求JSON参数").build();
        }
        if (null == userRequest.getOpenId()) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("JSON中缺少openId参数").build();
        }
        if (null == userRequest.getRole()) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("JSON中缺少Role参数").build();
        }
        try {
            String token = loginService.login(userRequest.getOpenId(), userRequest.getNickName(),
                    userRequest.getAvatarUrl(), userRequest.getGender(), userRequest.getRole());
            return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("登录成功").data(token).build();
        } catch (Exception e) {
            e.printStackTrace();
            return builder.code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务端内部错误").build();
        }
    }

    @PostMapping("/isLogin")
    public HttpResponse<Boolean> isLogin(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                .msg(loginService.isLogin(token) ? "已登录" : "未登录")
                .data(loginService.isLogin(token)).build();
    }
}

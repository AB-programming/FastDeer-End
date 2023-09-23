package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.school.SchoolRequest;
import com.deer.fastdeerend.service.SchoolService;
import com.deer.fastdeerend.util.model.SchoolLoginResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Resource
    private SchoolService schoolService;

    @PostMapping("/login")
    public HttpResponse<SchoolLoginResult> login(@RequestBody SchoolRequest schoolRequest) {
        HttpResponse.HttpResponseBuilder<SchoolLoginResult> builder = HttpResponse.builder();

        if (!StringUtils.hasText(schoolRequest.getUsername())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少username参数")
                    .build();
        }
        if (!StringUtils.hasText(schoolRequest.getPassword())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少password参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("登录成功")
                .data(schoolService.login(schoolRequest.getUsername(), schoolRequest.getPassword()))
                .build();
    }

    @GetMapping("/isLogin")
    public HttpResponse<Boolean> isLogin(HttpServletRequest request) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        String token = request.getHeader("Authorization");
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg(schoolService.isLogin(token) ? "已登录" : "未登录")
                .data(schoolService.isLogin(token))
                .build();
    }
}

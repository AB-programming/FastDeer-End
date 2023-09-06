package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.admin.AdminRequest;
import com.deer.fastdeerend.domain.dto.user.UserRequest;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.service.AdminService;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    public HttpResponse<String> login(@RequestBody AdminRequest adminRequest) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();
        if (!StringUtils.hasText(adminRequest.getUsername())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("用户名不能为空")
                    .build();
        }
        if (!StringUtils.hasText(adminRequest.getPassword())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("密码不能为空")
                    .build();
        }
        try {
            VerifyTokenResult result = adminService.login(adminRequest.getUsername(), adminRequest.getPassword());
                return builder
                        .code(result.isStatus() ? HttpResponseStatusCodeSet.OK.getValue() : HttpResponseStatusCodeSet.BadRequest.getValue())
                        .msg(result.isStatus() ? "登录成功" : "登录失败")
                        .data(result.getData())
                        .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .build();
        }
    }

    @GetMapping("/isLogin")
    public HttpResponse<Boolean> isLogin(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                .msg(adminService.isLogin(token) ? "已登录" : "未登录")
                .data(adminService.isLogin(token)).build();
    }

    @GetMapping("/selectAllUser")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public HttpResponse<List<User>> selectAllUser() {
        HttpResponse.HttpResponseBuilder<List<User>> builder = HttpResponse.builder();
        return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(adminService.selectAllUser()).build();
    }

    @DeleteMapping("/removeUserById")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public HttpResponse<Boolean> removeUserById(@RequestBody UserRequest userRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(userRequest.getOpenId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少id参数")
                    .build();
        }
        try {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("删除成功")
                    .data(adminService.removeUserById(userRequest.getOpenId()))
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .build();
        }
    }
}

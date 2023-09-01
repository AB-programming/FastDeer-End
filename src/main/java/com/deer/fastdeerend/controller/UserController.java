package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.bo.AvatarBo;
import com.deer.fastdeerend.domain.dto.user.UserRelateRequest;
import com.deer.fastdeerend.domain.dto.user.UserRequest;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.vo.post.userinfo.UserInfo;
import com.deer.fastdeerend.service.UserService;
import com.deer.fastdeerend.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private JWTUtil jwtUtil;

    @PostMapping("/updateUserById")
    public HttpResponse<Integer> updateUserById(@RequestBody UserRequest userRequest) {
        HttpResponse.HttpResponseBuilder<Integer> builder = HttpResponse.builder();
        if (null == userRequest) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("没有body参数")
                    .data(0).build();
        }
        if (null == userRequest.getOpenId()) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("openId不能为空")
                    .data(0).build();
        }

        Integer result = userService.updateUserById(userRequest.getOpenId(), userRequest.getNickName(), userRequest.getGender(),
                userRequest.getPlace(), userRequest.getBirth(), userRequest.getSchool(),
                userRequest.getMajor(), userRequest.getQualification(), userRequest.getGraduationDate());
        return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("信息修改成功")
                .data(result).build();
    }

    @PostMapping("/deleteUser")
    public HttpResponse<Integer> deleteUser(HttpServletRequest request) {

        HttpResponse.HttpResponseBuilder<Integer> builder = HttpResponse.builder();

        String token = request.getHeader("Authorization");

        try {
            User user = objectMapper.readValue(jwtUtil.verifyToken(token).getData(), User.class);

            if (null == user) {
                return builder.code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                        .msg("服务器内部错误，token中缺少UserInfo信息")
                        .build();
            }

            Integer result = userService.deleteUserById(user.getId(), token);
            return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("注销成功")
                    .data(result).build();
        } catch (JsonProcessingException e) {
            return builder.code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .build();
        }
    }

    @GetMapping("/getUserById")
    public HttpResponse<User> getUserById(String openId) {
        HttpResponse.HttpResponseBuilder<User> builder = HttpResponse.builder();
        if (openId == null) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少openId参数")
                    .build();
        }

        return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(userService.getUserById(openId)).build();
    }

    @PostMapping("/updateAvatar")
    public HttpResponse<String> updateAvatar(MultipartFile file, String openId) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();

        if (null == file) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("未收到头像图片")
                    .build();
        }

        if (null == openId || "".equals(openId)) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少openId参数")
                    .build();
        }

        AvatarBo avatarBo = userService.updateAvatar(file, openId);

        if (avatarBo.isStatus()) {
            return builder.code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("头像修改成功")
                    .data(avatarBo.getAvatarUrl())
                    .build();
        }

        return builder.code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                .msg("服务端异常，请稍后再试")
                .build();
    }

    @PostMapping("/incrementUserRelate")
    public HttpResponse<Boolean> incrementUserRelate(@RequestBody UserRelateRequest userRelateRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(userRelateRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(userRelateRequest.getTargetId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少targetId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("关注成功")
                .data(userService.incrementAttention(userRelateRequest.getUserId(), userRelateRequest.getTargetId()))
                .build();
    }

    @DeleteMapping("/decrementUserRelate")
    public HttpResponse<Boolean> decrementUserRelate(@RequestBody UserRelateRequest userRelateRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(userRelateRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(userRelateRequest.getTargetId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少targetId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("已取消关注")
                .data(userService.decrementAttention(userRelateRequest.getUserId(), userRelateRequest.getTargetId()))
                .build();
    }

    @GetMapping("/isAttention")
    public HttpResponse<Boolean> isAttention(String userId, String targetId) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(targetId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少targetId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(userService.isAttention(userId, targetId))
                .build();
    }

    @GetMapping("/selectUserRelateCountByUserId")
    public HttpResponse<Long> selectUserRelateCountByUserId(String userId) {
        HttpResponse.HttpResponseBuilder<Long> builder = HttpResponse.builder();

        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(userService.selectUserRelateCountByUserId(userId))
                .build();
    }

    @GetMapping("/getUserInfoByUserId")
    public HttpResponse<UserInfo> getUserInfoByUserId(String userId) {
        HttpResponse.HttpResponseBuilder<UserInfo> builder = HttpResponse.builder();

        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(userService.getUserInfoByUserId(userId))
                .build();
    }

    @GetMapping("/selectUserByKeyword")
    public HttpResponse<List<User>> selectUserByKeyword(String keyword) {
        HttpResponse.HttpResponseBuilder<List<User>> builder = HttpResponse.builder();

        if (!StringUtils.hasText(keyword)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少keyword参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(userService.selectUserByKeyword(keyword))
                .build();
    }
}

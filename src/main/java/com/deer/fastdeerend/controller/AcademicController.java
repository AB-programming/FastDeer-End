package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.service.AcademicService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/academic")
public class AcademicController {
    @Resource
    private AcademicService academicService;

    @PostMapping("/publish")
    public HttpResponse<Boolean> publish(MultipartFile file, String openId, String title, String content) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(openId)) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少openId参数").build();
        }
        if (!StringUtils.hasText(title)) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少title参数").build();
        }
        if (!StringUtils.hasText(content)) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少content参数").build();
        }
        if (null == file) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少file参数").build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("发布成功")
                .data(academicService.publish(openId, title, content, file, file.getOriginalFilename()))
                .build();
    }
}

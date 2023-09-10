package com.deer.fastdeerend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.vo.academic.AcademicDisplayVo;
import com.deer.fastdeerend.service.AcademicService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/academic")
public class AcademicController {
    @Resource
    private AcademicService academicService;

    @PostMapping("/publish")
    public HttpResponse<Boolean> publish(MultipartFile file, String openId, String date, String title, String content) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(openId)) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少openId参数").build();
        }
        if (!StringUtils.hasText(date)) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少date参数").build();
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
        try {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("发布成功")
                    .data(academicService.publish(openId, date, title, content, file, file.getOriginalFilename()))
                    .build();
        } catch (IOException | ClientException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误，请稍后再试")
                    .build();
        }
    }

    @GetMapping("/selectAcademicDisplayList")
    public HttpResponse<List<AcademicDisplayVo>> selectAcademicDisplayList() {
        HttpResponse.HttpResponseBuilder<List<AcademicDisplayVo>> builder = HttpResponse.builder();
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(academicService.selectAcademicDisplayList())
                .build();
    }

    @GetMapping("/getAcademicContentByAcademicId")
    public HttpResponse<String> getAcademicContentByAcademicId(String academicId) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();
        if (!StringUtils.hasText(academicId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少academicId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(academicService.getAcademicContentByAcademicId(academicId))
                .build();
    }
}

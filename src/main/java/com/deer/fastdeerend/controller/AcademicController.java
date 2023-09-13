package com.deer.fastdeerend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.academic.AcademicCommentRequest;
import com.deer.fastdeerend.domain.vo.academic.AcademicCommentVo;
import com.deer.fastdeerend.domain.vo.academic.AcademicDisplayVo;
import com.deer.fastdeerend.service.AcademicService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/selectAcademicCommentListByAcademicId")
    public HttpResponse<List<AcademicCommentVo>> selectAcademicCommentListByAcademicId(String academicId) {
        HttpResponse.HttpResponseBuilder<List<AcademicCommentVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(academicId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少academicId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(academicService.selectAcademicCommentListByAcademicId(academicId))
                .build();
    }

    @PostMapping("/sendAcademicComment")
    public HttpResponse<Boolean> sendAcademicComment(@RequestBody AcademicCommentRequest academicCommentRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(academicCommentRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(academicCommentRequest.getAcademicId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少academicId参数")
                    .build();
        }
        if (!StringUtils.hasText(academicCommentRequest.getDate())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少date参数")
                    .build();
        }
        if (!StringUtils.hasText(academicCommentRequest.getContent())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("评论不能为空")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("评论成功")
                .data(academicService.sendAcademicComment(academicCommentRequest.getUserId(), academicCommentRequest.getAcademicId(),
                        academicCommentRequest.getDate(), academicCommentRequest.getContent()))
                .build();
    }
}

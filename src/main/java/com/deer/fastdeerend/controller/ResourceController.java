package com.deer.fastdeerend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.vo.resource.ResourceVo;
import com.deer.fastdeerend.service.ResourceService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @PostMapping("uploadResource")
    public HttpResponse<Boolean> uploadResource(MultipartFile file, String userId, String date,
                                                String description) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(date)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少date参数")
                    .build();
        }
        if (!StringUtils.hasText(description)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少description参数")
                    .build();
        }
        if (null == file) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少file文件")
                    .build();
        }
        try {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("上传成功")
                    .data(resourceService.uploadResource(userId, date, description, file))
                    .build();
        } catch (IOException | ClientException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .build();
        }
    }

    @GetMapping("/selectResourceList")
    public HttpResponse<List<ResourceVo>> selectResourceList() {
        HttpResponse.HttpResponseBuilder<List<ResourceVo>> builder = HttpResponse.builder();
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(resourceService.selectResourceList())
                .build();
    }
}

package com.deer.fastdeerend.controller;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.vo.event.EventVo;
import com.deer.fastdeerend.service.EventService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Resource
    private EventService eventService;

    @PostMapping("/publishEvent")
    @PreAuthorize("hasRole('school')")
    public HttpResponse<Boolean> publishEvent(MultipartFile file,
                                              MultipartFile cover,
                                              String userId,
                                              String date,
                                              String title) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (null == file) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少file参数")
                    .build();
        }
        if (null == cover) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少cover参数")
                    .build();
        }
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
        if (!StringUtils.hasText(title)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少title参数")
                    .build();
        }
        try {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("发布成功")
                    .data(eventService.publishEvent(userId, date, title, file, cover))
                    .build();
        } catch (IOException | ClientException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .build();
        }
    }

    @GetMapping("selectEventListByUserId")
    public HttpResponse<List<EventVo>> selectEventListByUserId(String userId) {
        HttpResponse.HttpResponseBuilder<List<EventVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(eventService.selectEventListByUserId(userId))
                .build();
    }

    @GetMapping("/selectEventList")
    public HttpResponse<List<EventVo>> selectEventList() {
        HttpResponse.HttpResponseBuilder<List<EventVo>> builder = HttpResponse.builder();
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(eventService.selectEventList())
                .build();
    }

    @GetMapping("/getEventUrlByEventId")
    public HttpResponse<String> getEventUrlByEventId(String eventId) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();
        if (!StringUtils.hasText(eventId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少eventId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(eventService.getEventUrlByEventId(eventId))
                .build();
    }

    @DeleteMapping("/deleteEvent")
    @PreAuthorize("hasRole('school')")
    public HttpResponse<Boolean> deleteEvent(@RequestParam("eventId") String eventId) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(eventId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少eventId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("删除成功")
                .data(eventService.deleteEvent(eventId))
                .build();
    }
}

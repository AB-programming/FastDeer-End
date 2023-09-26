package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.volunteer.VolunteerRegistrationRequest;
import com.deer.fastdeerend.domain.dto.volunteer.VolunteerRequest;
import com.deer.fastdeerend.domain.vo.volunteer.VolunteerVo;
import com.deer.fastdeerend.service.VolunteerService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    @Resource
    private VolunteerService volunteerService;

    @PostMapping("/publishVolunteer")
    @PreAuthorize("hasRole('school')")
    public HttpResponse<Boolean> publishVolunteer(@RequestBody VolunteerRequest volunteerRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(volunteerRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }if (!StringUtils.hasText(volunteerRequest.getTitle())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少title参数")
                    .build();
        }
        if (!StringUtils.hasText(volunteerRequest.getDate())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少date参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("发布成功")
                .data(volunteerService.publishVolunteer(volunteerRequest.getUserId(),
                        volunteerRequest.getTitle(), volunteerRequest.getDescription(),
                        volunteerRequest.getDate(), volunteerRequest.getDeadline()))
                .build();
    }

    @DeleteMapping("/deleteVolunteer")
    @PreAuthorize("hasRole('school')")
    public HttpResponse<Boolean> deleteVolunteer(@RequestParam("volunteerId") String volunteerId) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(volunteerId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少volunteerId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("删除成功")
                .data(volunteerService.deleteVolunteer(volunteerId))
                .build();
    }

    @GetMapping("/selectAllVolunteer")
    public HttpResponse<List<VolunteerVo>> selectAllVolunteer() {
        HttpResponse.HttpResponseBuilder<List<VolunteerVo>> builder = HttpResponse.builder();
        return  builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(volunteerService.selectAllVolunteer())
                .build();
    }

    @PostMapping("/applyVolunteer")
    public HttpResponse<Boolean> applyVolunteer(@RequestBody VolunteerRegistrationRequest volunteerRegistrationRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(volunteerRegistrationRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(volunteerRegistrationRequest.getVolunteerId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少volunteerId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("报名成功")
                .data(volunteerService.applyVolunteer(volunteerRegistrationRequest.getUserId(),
                                volunteerRegistrationRequest.getVolunteerId()))
                .build();
    }

    @GetMapping("/selectVolunteerListBySchoolId")
    public HttpResponse<List<VolunteerVo>> selectVolunteerListBySchool(@RequestParam("schoolId") String schoolId) {
        HttpResponse.HttpResponseBuilder<List<VolunteerVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(schoolId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少schoolId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(volunteerService.selectVolunteerListBySchoolId(schoolId))
                .build();
    }
}

package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.job.JobRequest;
import com.deer.fastdeerend.domain.vo.job.JobVo;
import com.deer.fastdeerend.service.JobService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;

    @PostMapping("/publishJob")
    @PreAuthorize("hasRole('school')")
    public HttpResponse<Boolean> publishJob(@RequestBody JobRequest jobRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(jobRequest.getJobName())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少jobName参数")
                    .build();
        }
        if (!StringUtils.hasText(jobRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(jobRequest.getDate())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少date参数")
                    .build();
        }
        if (!StringUtils.hasText(jobRequest.getContact())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少contact参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("发布成功")
                .data(jobService.addJob(jobRequest.getJobName(), jobRequest.getUserId(),
                        jobRequest.getDegree(), jobRequest.getSalary(),
                        jobRequest.getDescription(), jobRequest.getCompany(),
                        jobRequest.getDate(), jobRequest.getDeadline(),
                        jobRequest.getContact()))
                .build();
    }

    @DeleteMapping("/deleteJob")
    @PreAuthorize("hasRole('school')")
    public HttpResponse<Boolean> deleteJob(@RequestParam("jobId") String jobId) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(jobId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少jobId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("删除成功")
                .data(jobService.deleteJob(jobId))
                .build();
    }

    @GetMapping("/selectAllJob")
    public HttpResponse<List<JobVo>> selectAllJob() {
        HttpResponse.HttpResponseBuilder<List<JobVo>> builder = HttpResponse.builder();
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(jobService.selectAllJob())
                .build();
    }

    @GetMapping("/selectJobListBySchoolId")
    public HttpResponse<List<JobVo>> selectJobListBySchoolId(@RequestParam("schoolId") String schoolId) {
        HttpResponse.HttpResponseBuilder<List<JobVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(schoolId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少schoolId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(jobService.selectJobListBySchoolId(schoolId))
                .build();
    }
}

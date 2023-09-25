package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.job.JobRequest;
import com.deer.fastdeerend.service.JobService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
    @Resource
    private JobService jobService;

    @PostMapping("/publishJob")
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
}

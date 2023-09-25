package com.deer.fastdeerend.service.impl;

import com.deer.fastdeerend.dao.job.JobMapper;
import com.deer.fastdeerend.domain.entity.job.Job;
import com.deer.fastdeerend.service.JobService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    @Resource
    private JobMapper jobMapper;

    @Override
    public Boolean addJob(String jobName, String userId, String degree, String salary,
                          String description, String company, String date, String deadline, String contact) {

        String jobId = UUID.randomUUID().toString();
        return jobMapper.insert(Job.builder()
                .jobId(jobId)
                .jobName(jobName)
                .userId(userId)
                .degree(degree)
                .salary(salary)
                .description(description)
                .company(company)
                .date(date)
                .deadline(deadline)
                .contact(contact)
                .build()) > 0;
    }
}

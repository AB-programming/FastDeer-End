package com.deer.fastdeerend.service.impl;

import com.deer.fastdeerend.dao.job.JobMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.job.Job;
import com.deer.fastdeerend.domain.vo.job.JobVo;
import com.deer.fastdeerend.service.JobService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    @Resource
    private JobMapper jobMapper;

    @Resource
    private UserMapper userMapper;

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

    @Override
    public Boolean deleteJob(String jobId) {
        return jobMapper.deleteById(jobId) > 0;
    }

    @Override
    public List<JobVo> selectAllJob() {
        List<Job> jobs = jobMapper.selectList(null);
        return jobs.parallelStream()
                .map(job -> JobVo.builder()
                        .jobId(job.getJobId())
                        .jobName(job.getJobName())
                        .userId(job.getUserId())
                        .nickName(userMapper.selectById(job.getUserId()).getNickName())
                        .avatar(userMapper.selectById(job.getUserId()).getAvatarUrl())
                        .degree(job.getDegree())
                        .salary(job.getSalary())
                        .description(job.getDescription())
                        .company(job.getCompany())
                        .date(job.getDate())
                        .deadline(job.getDeadline())
                        .contact(job.getContact())
                        .build())
                .toList();
    }
}

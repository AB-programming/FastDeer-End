package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.vo.job.JobVo;

import java.util.List;

public interface JobService {
    public Boolean addJob(String jobName, String userId, String degree, String salary,
                          String description, String company, String date, String deadline, String contact);

    public Boolean deleteJob(String jobId);

    public List<JobVo> selectAllJob();

    public List<JobVo> selectJobListBySchoolId(String schoolId);
}

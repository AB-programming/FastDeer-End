package com.deer.fastdeerend.service;

public interface JobService {
    public Boolean addJob(String jobName, String userId, String degree, String salary,
                          String description, String company, String date, String deadline, String contact);
}

package com.deer.fastdeerend.service;

import org.springframework.web.multipart.MultipartFile;

public interface AcademicService {
    public Boolean publish(String openId, String title, String content, MultipartFile file, String fileName);
}

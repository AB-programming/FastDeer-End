package com.deer.fastdeerend.service.impl;

import com.deer.fastdeerend.service.AcademicService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AcademicServiceImpl implements AcademicService {
    @Override
    public Boolean publish(String openId, String title, String content, MultipartFile file, String fileName) {
        // TODO
        return null;
    }
}

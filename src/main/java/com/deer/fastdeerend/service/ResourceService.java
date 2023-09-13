package com.deer.fastdeerend.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ResourceService {
    public Boolean uploadResource(String userId, String date, String description, MultipartFile file) throws IOException, ClientException;
}

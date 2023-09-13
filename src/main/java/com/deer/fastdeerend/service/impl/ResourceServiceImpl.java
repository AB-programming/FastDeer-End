package com.deer.fastdeerend.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.dao.resource.ResourceMapper;
import com.deer.fastdeerend.service.ResourceService;
import com.deer.fastdeerend.util.OSSUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Value("${oss.ossAddress}")
    public String ossAddress;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private OSSUtil ossUtil;

    @Override
    public Boolean uploadResource(String userId, String date, String description, MultipartFile file) throws IOException, ClientException {
        String resourceId = UUID.randomUUID().toString();

        String fileName = StringUtils.getFilename(file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        ossUtil.uploadFile("resource", resourceId + "." + extension, file.getBytes());
        return resourceMapper.insert(com.deer.fastdeerend.domain.entity.resource.Resource.builder()
                .resourceId(resourceId)
                .userId(userId)
                .date(date)
                .description(description)
                .fileName(fileName)
                .extension(extension)
                .url(ossAddress + "resource/" + resourceId + "." + extension)
                .build()) > 0;
    }
}

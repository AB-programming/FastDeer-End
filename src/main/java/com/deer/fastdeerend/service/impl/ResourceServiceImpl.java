package com.deer.fastdeerend.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.resource.ResourceMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.vo.resource.ResourceVo;
import com.deer.fastdeerend.service.ResourceService;
import com.deer.fastdeerend.util.OSSUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Value("${oss.ossAddress}")
    public String ossAddress;

    @Resource
    private ResourceMapper resourceMapper;

    @Resource
    private UserMapper userMapper;

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

    @Override
    public List<ResourceVo> selectResourceList() {
        List<com.deer.fastdeerend.domain.entity.resource.Resource> resources = resourceMapper.selectList(new QueryWrapper<>());
        return resources.parallelStream().map(resource -> ResourceVo.builder()
                        .resourceId(resource.getResourceId())
                        .userId(resource.getUserId())
                        .name(userMapper.selectById(resource.getUserId()).getNickName())
                        .avatar(userMapper.selectById(resource.getUserId()).getAvatarUrl())
                        .date(resource.getDate())
                        .url(resource.getUrl())
                        .description(resource.getDescription())
                        .fileName(resource.getFileName())
                        .build())
                .collect(Collectors.toList());
    }
}

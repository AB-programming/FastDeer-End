package com.deer.fastdeerend.service;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.domain.vo.resource.ResourceVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ResourceService {
    public Boolean uploadResource(String userId, String date, String description, MultipartFile file) throws IOException, ClientException;

    public List<ResourceVo> selectResourceList();
}

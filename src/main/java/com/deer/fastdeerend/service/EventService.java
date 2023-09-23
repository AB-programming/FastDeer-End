package com.deer.fastdeerend.service;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.domain.vo.event.EventVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    public Boolean publishEvent(String userId, String date, String title, MultipartFile file, MultipartFile cover) throws IOException, ClientException;

    public List<EventVo> selectEventListByUserId(String userId);

    public List<EventVo> selectEventList();
}

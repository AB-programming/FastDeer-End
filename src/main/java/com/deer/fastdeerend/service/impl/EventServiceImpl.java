package com.deer.fastdeerend.service.impl;

import com.aliyun.oss.model.VoidResult;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.event.EventMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.event.Event;
import com.deer.fastdeerend.domain.vo.event.EventVo;
import com.deer.fastdeerend.service.EventService;
import com.deer.fastdeerend.util.OSSUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Value("${oss.ossAddress}")
    public String ossAddress;

    @Resource
    private EventMapper eventMapper;

    @Resource
    private OSSUtil ossUtil;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public Boolean publishEvent(String userId, String date, String title, MultipartFile file, MultipartFile cover) throws IOException, ClientException {
        String eventId = UUID.randomUUID().toString();
        String eventExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String coverExtension = StringUtils.getFilenameExtension(cover.getOriginalFilename());
        ossUtil.uploadFile("event", eventId + '.' + eventExtension, file.getBytes());
        ossUtil.uploadFile("event/cover", eventId + '.' + coverExtension, cover.getBytes());
        return eventMapper.insert(Event.builder()
                .eventId(eventId)
                .userId(userId)
                .date(date)
                .title(title)
                .url(ossAddress + "event/" + eventId + '.' + eventExtension)
                .cover(ossAddress + "event/cover/" + eventId + '.' + coverExtension)
                .build()) > 0;
    }

    @Override
    public List<EventVo> selectEventListBySchoolId(String userId) {
        List<Event> events = eventMapper.selectList(new QueryWrapper<Event>()
                .lambda()
                .eq(Event::getUserId, userId));
        return events.parallelStream()
                .map(event -> EventVo.builder()
                        .eventId(event.getEventId())
                        .userId(userId)
                        .name(userMapper.selectById(userId).getNickName())
                        .avatar(userMapper.selectById(userId).getAvatarUrl())
                        .title(event.getTitle())
                        .cover(event.getCover())
                        .date(event.getDate())
                        .build())
                .toList();
    }

    @Override
    public List<EventVo> selectEventList() {
        List<Event> events = eventMapper.selectList(new QueryWrapper<>());
        return events.parallelStream()
                .map(event -> EventVo.builder()
                        .eventId(event.getEventId())
                        .userId(event.getUserId())
                        .name(userMapper.selectById(event.getUserId()).getNickName())
                        .avatar(userMapper.selectById(event.getUserId()).getAvatarUrl())
                        .title(event.getTitle())
                        .cover(event.getCover())
                        .date(event.getDate())
                        .build())
                .toList();
    }

    @Override
    public String getEventUrlByEventId(String eventId) {
        return eventMapper.selectById(eventId).getUrl();
    }

    @Override
    @Transactional
    public Boolean deleteEvent(String eventId) {
        ossUtil.deleteFile("event", eventId + ".html");
        String cover = eventMapper.selectById(eventId).getCover();
        String extension = StringUtils.getFilenameExtension(cover);
        ossUtil.deleteFile("event/cover", eventId + '.' + extension);
        return eventMapper.deleteById(eventId) > 0;
    }
}

package com.deer.fastdeerend.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.academic.AcademicMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.academic.Academic;
import com.deer.fastdeerend.domain.vo.academic.AcademicDisplayVo;
import com.deer.fastdeerend.service.AcademicService;
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
import java.util.stream.Collectors;

@Service
public class AcademicServiceImpl implements AcademicService {
    @Value("${oss.ossAddress}")
    private String ossAddress;

    @Resource
    private OSSUtil ossUtil;

    @Resource
    private AcademicMapper academicMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public Boolean publish(String userId, String date, String title, String content, MultipartFile file, String fileName) throws IOException, ClientException {
        String academicId = UUID.randomUUID().toString();
        ossUtil.uploadFile("academic",
                academicId + "." + StringUtils.getFilenameExtension(fileName),
                file.getBytes());

        return academicMapper.insert(Academic.builder()
                .academicId(academicId)
                .userId(userId)
                .date(date)
                .title(title)
                .content(content)
                .cover(ossAddress + "academic/" + academicId + "." + StringUtils.getFilenameExtension(fileName))
                .build()) > 0;
    }

    @Override
    public List<AcademicDisplayVo> selectAcademicDisplayList() {
        List<Academic> academics = academicMapper.selectList(new QueryWrapper<Academic>().orderByAsc("date"));
        return academics.parallelStream()
                .map(academic -> AcademicDisplayVo.builder()
                        .academicId(academic.getAcademicId())
                        .userId(academic.getUserId())
                        .name(userMapper.selectById(academic.getUserId()).getNickName())
                        .avatar(userMapper.selectById(academic.getUserId()).getAvatarUrl())
                        .title(academic.getTitle())
                        .date(academic.getDate())
                        .cover(academic.getCover())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public String getAcademicContentByAcademicId(String academicId) {
        return academicMapper.selectById(academicId).getContent();
    }
}

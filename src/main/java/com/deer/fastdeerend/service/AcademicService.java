package com.deer.fastdeerend.service;

import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.domain.vo.academic.AcademicCommentVo;
import com.deer.fastdeerend.domain.vo.academic.AcademicDisplayVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AcademicService {
    public Boolean publish(String userId, String date, String title, String content, MultipartFile file, String fileName) throws IOException, ClientException;

    public List<AcademicDisplayVo> selectAcademicDisplayList();

    public String getAcademicContentByAcademicId(String academicId);

    public List<AcademicCommentVo> selectAcademicCommentListByAcademicId(String academicId);

    public Boolean sendAcademicComment(String userId, String academicId, String date, String content);
}

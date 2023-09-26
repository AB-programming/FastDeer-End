package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.vo.volunteer.VolunteerVo;

import java.util.List;

public interface VolunteerService {
    public Boolean publishVolunteer(String userId, String title, String description
            , String date, String deadline);

    public Boolean deleteVolunteer(String volunteerId);

    public List<VolunteerVo> selectAllVolunteer();

    public Boolean applyVolunteer(String userId, String volunteerId);

    public List<VolunteerVo> selectVolunteerListBySchoolId(String schoolId);
}

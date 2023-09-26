package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.dao.volunteer.VolunteerMapper;
import com.deer.fastdeerend.dao.volunteer.VolunteerRegistrationMapper;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.entity.volunteer.Volunteer;
import com.deer.fastdeerend.domain.entity.volunteer.VolunteerRegistration;
import com.deer.fastdeerend.domain.vo.volunteer.VolunteerApplicant;
import com.deer.fastdeerend.domain.vo.volunteer.VolunteerVo;
import com.deer.fastdeerend.service.VolunteerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Resource
    private VolunteerMapper volunteerMapper;

    @Resource
    private VolunteerRegistrationMapper volunteerRegistrationMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean publishVolunteer(String userId, String title, String description, String date, String deadline) {
        String volunteerId = UUID.randomUUID().toString();
        return volunteerMapper.insert(Volunteer.builder()
                .volunteerId(volunteerId)
                .userId(userId)
                .title(title)
                .description(description)
                .date(date)
                .deadline(deadline)
                .build()) > 0;
    }

    @Override
    public Boolean deleteVolunteer(String volunteerId) {
        return volunteerMapper.deleteById(volunteerId) > 0;
    }

    @Override
    public List<VolunteerVo> selectAllVolunteer() {
        return volunteerMapper.selectList(null)
                .parallelStream()
                .map(volunteer -> {
                    List<VolunteerRegistration> volunteerRegistrations = volunteerRegistrationMapper.selectList(new QueryWrapper<VolunteerRegistration>()
                            .lambda()
                            .eq(VolunteerRegistration::getVolunteerId, volunteer.getVolunteerId()));
                    List<VolunteerApplicant> members = new ArrayList<>();
                    volunteerRegistrations.forEach(volunteerRegistration -> {
                        User user = userMapper.selectById(volunteerRegistration.getUserId());
                        members.add(VolunteerApplicant.builder()
                                .userId(user.getId())
                                .name(user.getNickName())
                                .avatar(user.getAvatarUrl())
                                .gender(user.getGender())
                                .school(user.getSchool())
                                .major(user.getMajor())
                                .qualification(user.getQualification())
                                .build());
                    });
                    return VolunteerVo.builder()
                            .volunteerId(volunteer.getVolunteerId())
                            .userId(volunteer.getUserId())
                            .name(userMapper.selectById(volunteer.getUserId()).getNickName())
                            .avatar(userMapper.selectById(volunteer.getUserId()).getNickName())
                            .title(volunteer.getTitle())
                            .description(volunteer.getDescription())
                            .date(volunteer.getDate())
                            .deadline(volunteer.getDeadline())
                            .members(members)
                            .build();
                })
                .toList();
    }

    @Override
    public Boolean applyVolunteer(String userId, String volunteerId) {
        String volunteerRegistrationId = UUID.randomUUID().toString();
        return volunteerRegistrationMapper.insert(VolunteerRegistration.builder()
                .volunteerRegistrationId(volunteerRegistrationId)
                .userId(userId)
                .volunteerId(volunteerId)
                .build()) > 0;
    }

    @Override
    public List<VolunteerVo> selectVolunteerListBySchoolId(String schoolId) {
        return volunteerMapper.selectList(new QueryWrapper<Volunteer>()
                        .lambda()
                        .eq(Volunteer::getUserId, schoolId))
                .parallelStream()
                .map(volunteer -> {
                    List<VolunteerRegistration> volunteerRegistrations = volunteerRegistrationMapper.selectList(new QueryWrapper<VolunteerRegistration>()
                            .lambda()
                            .eq(VolunteerRegistration::getVolunteerId, volunteer.getVolunteerId()));
                    List<VolunteerApplicant> members = new ArrayList<>();
                    volunteerRegistrations.forEach(volunteerRegistration -> {
                        User user = userMapper.selectById(volunteerRegistration.getUserId());
                        members.add(VolunteerApplicant.builder()
                                .userId(user.getId())
                                .name(user.getNickName())
                                .avatar(user.getAvatarUrl())
                                .gender(user.getGender())
                                .school(user.getSchool())
                                .major(user.getMajor())
                                .qualification(user.getQualification())
                                .build());
                    });
                    return VolunteerVo.builder()
                            .volunteerId(volunteer.getVolunteerId())
                            .userId(volunteer.getUserId())
                            .name(userMapper.selectById(volunteer.getUserId()).getNickName())
                            .avatar(userMapper.selectById(volunteer.getUserId()).getNickName())
                            .title(volunteer.getTitle())
                            .description(volunteer.getDescription())
                            .date(volunteer.getDate())
                            .deadline(volunteer.getDeadline())
                            .members(members)
                            .build();
                })
                .toList();
    }
}

package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AdminService {
    public VerifyTokenResult login(String username, String password) throws JsonProcessingException;

    public Boolean isLogin(String token);

    public List<User> selectAllUser();
}

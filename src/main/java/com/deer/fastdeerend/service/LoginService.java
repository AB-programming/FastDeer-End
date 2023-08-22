package com.deer.fastdeerend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface LoginService {
    public String login(String id, String nickName, String avatarUrl, String gender, String role) throws JsonProcessingException;

    public Boolean isLogin(String token);
}

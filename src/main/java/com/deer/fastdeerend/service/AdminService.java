package com.deer.fastdeerend.service;

import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdminService {
    public VerifyTokenResult login(String username, String password) throws JsonProcessingException;

    public Boolean isLogin(String token);
}

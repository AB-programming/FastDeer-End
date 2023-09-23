package com.deer.fastdeerend.service;

import com.deer.fastdeerend.util.model.SchoolLoginResult;

public interface SchoolService {
    public SchoolLoginResult login(String username, String password);

    public Boolean isLogin(String token);
}

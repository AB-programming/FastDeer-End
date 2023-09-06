package com.deer.fastdeerend;

import com.deer.fastdeerend.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AdminTests {

    @Resource
    private AdminService adminService;

    @Test
    public void removeUserById() {
        Boolean res = null;
        try {
            res = adminService.removeUserById("1");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info(res.toString());
    }
}

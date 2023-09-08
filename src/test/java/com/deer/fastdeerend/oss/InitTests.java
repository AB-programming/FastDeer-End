package com.deer.fastdeerend.oss;

import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import com.deer.fastdeerend.util.OSSUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResourceLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class InitTests {
    @Resource
    private OSSUtil ossUtil;

    @Test
    public void testAddFile() {
        InputStream resourceAsStream = Objects.requireNonNull(new FileSystemResourceLoader().getClassLoader()).getResourceAsStream("static/logo.png");
        PutObjectResult putObjectResult = null;
        try {
            assert resourceAsStream != null;
            putObjectResult = ossUtil.uploadFile("example", "logo.png", resourceAsStream.readAllBytes());
        } catch (ClientException | IOException e) {
            throw new RuntimeException(e);
        }
        log.info("etag= " + putObjectResult.getETag() + " version= " + putObjectResult.getVersionId());
    }
}

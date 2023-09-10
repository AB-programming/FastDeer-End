package com.deer.fastdeerend;

import com.deer.fastdeerend.dao.academic.AcademicMapper;
import com.deer.fastdeerend.domain.entity.academic.Academic;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AcademicTests {

    @Resource
    private AcademicMapper academicMapper;

    @Test
    public void testAddAcademic() {
        Academic academic = Academic.builder()
                .academicId("1")
                .userId("1")
                .date("2020-06-01 11:23:00")
                .title("Title")
                .content("<h1>Hello</h1><p>This is a test</p>")
                .cover("http://localhost:8080/static/logo.png")
                .build();
        int insert = academicMapper.insert(academic);
        log.info("insert result: {}", insert);
    }

    @Test
    public void testGetExtension() {
        String fileName= "logo.jpg";
        String filenameExtension = StringUtils.getFilenameExtension(fileName);
        log.info("filenameExtension: {}", filenameExtension);
    }
}

package com.deer.fastdeerend.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;
import com.aliyuncs.exceptions.ClientException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *  自定义封装OSS工具类
 *
 * @author AB-style
 * @date 2023/09/09
 */
@Component
public class OSSUtil {
    @Resource
    private OSS oss;

    @Value("${oss.bucketName}")
    private String bucketName;

    public PutObjectResult uploadFile(String directory, String fileName, byte[] bytes) throws ClientException {
        return oss.putObject(bucketName, directory + '/' + fileName, new ByteArrayInputStream(bytes));
    }

    public VoidResult deleteFile(String directory, String fileName) {
        return oss.deleteObject(bucketName, directory + '/' + fileName);
    }
}

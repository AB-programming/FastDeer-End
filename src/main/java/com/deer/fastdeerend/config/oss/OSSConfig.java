package com.deer.fastdeerend.config.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  OSS对象存储配置类
 *
 * @author AB-style
 * @date 2023/09/09
 */
@Configuration
public class OSSConfig {
    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Bean
    public OSS oss() throws ClientException {
        CredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        OSS oss = new OSSClientBuilder().build(endpoint, credentialsProvider);
        oss.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        return oss;
    }
}

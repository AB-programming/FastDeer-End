package com.deer.fastdeerend.config.mvc;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${dev.staticUrl}")
    private String staticUrl;

    @Value("${dev.maxFileSize}")
    private long maxFileSize;

    @Value("${dev.maxRequestSize}")
    private long maxRequestSize;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type","X-Requested-With","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(staticUrl);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize(DataSize.ofMegabytes(maxFileSize));
        multipartConfigFactory.setMaxRequestSize(DataSize.ofMegabytes(maxRequestSize));
        return multipartConfigFactory.createMultipartConfig();
    }
}

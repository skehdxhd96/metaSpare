package com.example.metauniversity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**********************
     * 정적 리소스 설정
     * RestDocs의 html 파일들이 Static Resource로 들어가지기 때문에 필요함
     *********************/
    private static final String [] RESOURCE_LOCATIONS = {
            "classpath:/static/"
    };

    private final String encoding = "UTF-8";
}
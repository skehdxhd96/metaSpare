package com.example.metauniversity.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FolderConfig {

    private String user = "user";
    private String board = "board";    
}

package com.example.metauniversity.domain.File.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class fileDto {

    private String extension; // 확장자
    private String originalName;
    private String fileName;
    private String url; // url
}

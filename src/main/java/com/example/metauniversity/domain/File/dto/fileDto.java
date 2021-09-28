package com.example.metauniversity.domain.File.dto;

import java.time.LocalDateTime;

import com.example.metauniversity.domain.board.dto.boardDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class fileDto {

    private String extension; // 확장자
    private String originalName;
    private String fileName;
    private String url; // url
    
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class fileUrl {
    	private String url; // url
    }
    
}

package com.example.metauniversity.domain.board.dto;

import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.UsersData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import javax.persistence.*;

public class boardDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class boardList {
    	private Long boardId;
        private String title;
        private LocalDateTime created_date;
        private String userName;
    }
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getBoard {
    	private Long boardId;
        private String title;
        private String content;
        private String userName;
    }
    
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class saveBoard {
    	private Long accountId;
        private String title;
        private String content;
    }

}

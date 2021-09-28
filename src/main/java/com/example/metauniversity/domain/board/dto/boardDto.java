package com.example.metauniversity.domain.board.dto;

import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.UsersData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
        private List<File> filesList;
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

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateBoard {
        private Long boardId;
        private String title;
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class pageBoardList {
        private int pageSize; // 한 페이지 데이터 수
        private int pageNumber; // 현재 페이지
        private int totalPages; // 전체 페이지 수
        private Long totalElements; // 전체 데이터 수
        List<boardDto.boardList> boardDtoList;
    }

}
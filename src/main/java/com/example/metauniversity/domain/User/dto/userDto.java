package com.example.metauniversity.domain.User.dto;

import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UserTyped;
import com.example.metauniversity.domain.User.UsersData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class userDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class signIn {
        private String accountId;
        private String userPassword;
        private String userEmail;
        private String userCode;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class signUp {
        private String accountId;
        private String password;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMyInfoResponse {
        private Long id;
        private String userName;
        private String userBirth;
        private String userPhone;
        private String userEmail;
        private String Address;

        private String userCode;
        private String userDepartment;
        private String userMajor;
        private String userMinor;
        private Integer userGrade;
        private String accountId;
        private EnrollmentStatus enrollmentStatus;
        private String thumbnailUrl;

        public getMyInfoResponse(User user) {
            this.id = user.getId();
            this.userName = user.getUsersData().getUserName();
            this.userBirth = user.getUsersData().getUserBirth();
            this.userPhone = user.getUsersData().getUserPhone();
            this.userEmail = user.getUsersData().getUserEmail();
            this.Address = user.getUsersData().getAddress();
            this.userCode = user.getUsersData().getUserCode();
            this.userDepartment = user.getUsersData().getUserDepartment();
            this.userMajor = user.getUsersData().getUserMajor();
            this.userMinor = user.getUsersData().getUserMinor();
            this.userGrade = user.getUsersData().getUserGrade();
            this.enrollmentStatus = user.getUsersData().getEnrollmentStatus();
            this.accountId = user.getAccountId();
            this.thumbnailUrl = user.getUsersData().getIsThumbnail() ? user.getUserfile().getFile().getUrl() : "img/cute.png";
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class update {
        private MultipartFile thumbnail;
        private String userName;
        private String userEmail;
        private String Address;
        private String userBirth;
        private String userPhone;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateResponse {
        private String thumbnailUrl;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchDto {
        private String userDepartment;
        private String userName;
        private String userMajor;
        private String userCode;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchResponse {
        private String userCode;
        private String userDepartment;
        private String userMajor;
        private String userName;
        private String userGrade;
        private EnrollmentStatus enrollmentStatus;

        public searchResponse(User user) {
            userCode = user.getUsersData().getUserCode();
            userDepartment = user.getUsersData().getUserDepartment();
            userMajor = user.getUsersData().getUserMajor();
            userGrade = user.getUsersData().getUserMajor();
            enrollmentStatus = user.getUsersData().getEnrollmentStatus();
            userName = user.getUsersData().getUserName();
        }
    }
}

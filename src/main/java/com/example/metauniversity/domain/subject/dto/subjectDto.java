package com.example.metauniversity.domain.subject.dto;

import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class subjectDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class create {

        private String subjectTitle;
        private String professorName;
        private Integer subjectPoints;
        private Boolean isMajor;
        private Integer subjectGrade;
        private Integer limited;
        private String classRoom;
        private String day;
        private String subjectIntro;
        private String subjectDepaetment;
        private String startTime;
        private String endTime;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getList {

        private String professor;
        private String subjectTitle;
        private Integer subjectPoints;
        private String isMajor;
        private String subjectDepaetment;
        private Integer subjectGrades;
        private Integer limited;
        private String classRoom;
        private String day;
        private String startTime;
        private String endTime;

        public getList(subject subject) {
            this.professor = subject.getUser().getUsersData().getUserName();
            this.subjectTitle = subject.getSubjectTitle();
            this.isMajor = subject.getIsMajor() ? "전공" : "교양";
            this.subjectPoints = subject.getSubjectPoints();
            this.subjectGrades = subject.getSubjectGrades();
            this.limited = subject.getLimited();
            this.classRoom = subject.getClassRoom();
            this.day = subject.getDay();
            this.startTime = subject.getStartTime();
            this.endTime = subject.getEndTime();
        }

        public getList(timeTable timeTable) {
            this.professor = timeTable.getUser().getUsersData().getUserName();
            this.subjectTitle = timeTable.getSubject().getSubjectTitle();
            this.subjectPoints = timeTable.getSubject().getSubjectPoints();
            this.subjectGrades = timeTable.getSubject().getSubjectGrades();
            this.limited = timeTable.getSubject().getLimited();
            this.classRoom = timeTable.getSubject().getClassRoom();
            this.day = timeTable.getSubject().getDay();
            this.startTime = timeTable.getSubject().getStartTime();
            this.endTime = timeTable.getSubject().getEndTime();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class search {
        private String subjectTitle;
        private Integer subjectPoints;
        private String isMajor;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class enroll {
        private String subjectTitle;
        private Boolean status;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userdata {
        private Long userId;
        private String userName;
    }
}

package com.example.metauniversity.domain.subject;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class subject extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "subject")
    private List<timeTable> timeTables = new ArrayList<>();

    private String subjectTitle;
    private Integer subjectPoints;
    private String subjectIntro;
    private String subjectDepartment;
    private Integer subjectGrades;
    /**
     * isMajor 추가해야함.
     */
    private Integer limited;
    private Boolean isMajor;
    private String classRoom;
    private String day;
    private String startTime;
    private String endTime;

    public static subject create(subjectDto.create createDto, User user) {
        return subject.builder()
                .user(user)
                .subjectTitle(createDto.getSubjectTitle())
                .subjectIntro(createDto.getSubjectIntro())
                .isMajor(createDto.getIsMajor())
                .subjectDepartment(createDto.getSubjectDepaetment())
                .subjectPoints(createDto.getSubjectPoints())
                .subjectGrades(createDto.getSubjectGrade())
                .limited(createDto.getLimited())
                .classRoom(createDto.getClassRoom())
                .day(createDto.getDay())
                .startTime(createDto.getStartTime())
                .endTime(createDto.getEndTime())
                .build();
    }
}

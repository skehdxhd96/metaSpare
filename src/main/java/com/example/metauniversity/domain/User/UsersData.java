package com.example.metauniversity.domain.User;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "usersData")
@Getter
public class UsersData {

    @Id
    @Column(name = "userCode")
    private String userCode;

    @JsonIgnore
    @OneToOne(mappedBy = "usersData")
    private User user;

    @Enumerated(EnumType.STRING)
    private UserTyped userType;

    private String userName;
    private String userBirth;
    private String userPhone;
    private String userEmail;
    private String Address;
    private String userDepartment;

    /**
     * Student
     */
    private String userMajor;
    private String userMinor;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;
    private Integer userGrade;

    /**
     * Worker
     */
    private String workerSpot;
}

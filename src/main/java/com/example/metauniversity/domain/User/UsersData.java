package com.example.metauniversity.domain.User;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.User.dto.userDto.getMyInfoResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "usersData")
@Getter
@Builder
@DynamicUpdate
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
    private Boolean isThumbnail;

    /**
     * Worker
     */
    private String workerSpot;

    public void setIsThumbnail() {
        isThumbnail = true;
    }

    // 개인 정보 수정
    public void update(userDto.update updateDto) {

        if(updateDto.getThumbnail().getOriginalFilename().length() != 0) {
            setIsThumbnail();
        }
		this.userName = updateDto.getUserName();
    	this.userBirth = updateDto.getUserBirth();
    	this.userPhone = updateDto.getUserPhone();
    	this.userEmail = updateDto.getUserEmail();
    	this.Address = updateDto.getAddress();
	}
    
    // 휴학, 복학 신청
    public void updateEnroll(EnrollmentStatus enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
}

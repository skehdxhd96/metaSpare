package com.example.metauniversity.domain.User;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.File.UserFile;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.subject.subject;
import com.example.metauniversity.domain.subject.timeTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @OneToOne(mappedBy = "user")
    private UserFile userfile;

    @OneToMany(mappedBy = "user")
    private List<subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<timeTable> timeTables = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCode")
    private UsersData usersData;

    private String confirmEmail;
    private String accountId;
    private String userPassword;
}

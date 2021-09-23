package com.example.metauniversity.domain.File;

import com.example.metauniversity.domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFile {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "userfileId")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "File_Id")
    private File file;

    public static UserFile create(File file, User user) {
        return UserFile.builder()
                .file(file)
                .user(user)
                .build();
    }
}

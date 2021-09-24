package com.example.metauniversity.domain.subject;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class timeTable extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeTableId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private Boolean status;

    public void setStatus() {
        status = status == true ? false : true;
    }

    public static timeTable create(User user, subject subject) {
        return timeTable.builder()
                .subject(subject)
                .user(user)
                .status(true)
                .build();
    }
}

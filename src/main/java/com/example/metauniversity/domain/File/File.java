package com.example.metauniversity.domain.File;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.File.dto.fileDto;
import com.example.metauniversity.domain.User.dto.userDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseEntity {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "File_Id")
    private Long id;

    @OneToOne(mappedBy = "file")
    private UserFile userFile;

    private String extension; // 확장자
    private String originalName;
    private String fileName;
    private String url; // url

    public static File create(fileDto fileDto) {
        return File.builder()
                .fileName(fileDto.getFileName())
                .extension(fileDto.getExtension())
                .originalName(fileDto.getOriginalName())
                .url(fileDto.getUrl())
                .build();
    }

}

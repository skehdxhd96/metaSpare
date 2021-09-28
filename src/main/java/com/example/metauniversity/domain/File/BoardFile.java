package com.example.metauniversity.domain.File;

import com.example.metauniversity.domain.board.Board;

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
public class BoardFile {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "boardfileId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "File_Id")
    private File file;

    public static BoardFile create(File file, Board board) {
        return BoardFile.builder()
                .file(file)
                .board(board)
                .build();
    }
}

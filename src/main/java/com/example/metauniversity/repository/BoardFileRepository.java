package com.example.metauniversity.repository;

import com.example.metauniversity.domain.File.BoardFile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
	void deleteByFileId(Long fileId);
	List<BoardFile> findByBoardBoardId(Long boardId);
	
	void deleteByBoardBoardId(Long boardId);
}

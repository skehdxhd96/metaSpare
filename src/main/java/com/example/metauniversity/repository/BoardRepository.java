package com.example.metauniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.metauniversity.domain.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	

}

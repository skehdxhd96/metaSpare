package com.example.metauniversity.service;

import com.example.metauniversity.config.FolderConfig;
import com.example.metauniversity.domain.File.File;
import com.example.metauniversity.domain.File.UserFile;
import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.User;
import com.example.metauniversity.domain.User.UsersData;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.board.Board;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.domain.board.dto.boardDto.boardList;
import com.example.metauniversity.domain.board.dto.boardDto.getBoard;
import com.example.metauniversity.domain.board.dto.boardDto.saveBoard;
import com.example.metauniversity.exception.NoSuchBoardException;
import com.example.metauniversity.exception.NoSuchUserException;
import com.example.metauniversity.repository.BoardRepository;
import com.example.metauniversity.repository.UserFileRepository;
import com.example.metauniversity.repository.UserRepository;
import com.example.metauniversity.repository.UsersDataRepository;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시글 목록 조회
	public List<boardList> getBoardList() {
		
		List<Board> boards = boardRepository.findAll();
		List<boardDto.boardList> boardDtoList = new ArrayList<>();
		
		for (Board board : boards) {
			boardDto.boardList boarddto = boardDto.boardList.builder()
					.boardId(board.getBoardId())
					.created_date(board.getCreatedDate())
					.title(board.getTitle())
					.userName(board.getUser().getUsersData().getUserName())
					.build();
			
			boardDtoList.add(boarddto);
		}
		
		return boardDtoList;
	}

	// 게시글 상세 조회
	public getBoard getBoard(Long boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NoSuchBoardException("해당 게시물이 없습니다."));
		
		boardDto.getBoard boarddto = boardDto.getBoard.builder()
					.boardId(board.getBoardId())
					.content(board.getContent())
					.title(board.getTitle())
					.userName(board.getUser().getUsersData().getUserName())
					.build();
				
		return boarddto;
	}

	// 게시글 삭제
	@Transactional
	public void deleteBoard(Long boardId) {
		boardRepository.deleteById(boardId);		
	}

	// 게시글 등록
	@Transactional
	public void saveBoard(saveBoard boarddto, User user) {
		
		boardRepository.save(Board.builder()
				.user(user)
				.title(boarddto.getTitle())
				.content(boarddto.getContent())
				.build());
	}

	
}

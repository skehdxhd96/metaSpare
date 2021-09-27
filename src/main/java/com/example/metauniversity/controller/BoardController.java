package com.example.metauniversity.controller;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.domain.board.dto.boardDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
	

    /**
     * 공지사항 목록 조회
     */
    @GetMapping("/boardList")
    public String boardList(Model model) {
    	List<boardDto.boardList> boardDtoList = boardService.getBoardList();
        model.addAttribute("boardList", boardDtoList);
        return "boardList";
    }
    
    /**
     * 공지사항 상세 조회
     */
    @GetMapping("/boardDetail/{boardId}")
    public String boardDetail(@PathVariable("boardId") Long boardId, Model model) {
    	boardDto.getBoard boardDto = boardService.getBoard(boardId);

        model.addAttribute("boardDto", boardDto);
        return "boardContent";
    }
    
    /**
     * 공지사항 삭제
     */
    @GetMapping("/boardDelete/{boardId}")
    public String boardDelete(@PathVariable("boardId") Long boardId) {
    	boardService.deleteBoard(boardId);
    	
    	return "redirect:/boardList";
    }
    
    /**
     * 공지사항 등록 페이지
     */
    @GetMapping("/boardForm")
    public String boardForm() {
    	
    	return "boardForm";
    }
    
    /**
     * 공지사항 등록
     */
    @PostMapping("/boardForm")
    public String boardForm(@ModelAttribute boardDto.saveBoard boarddto, @AuthenticationPrincipal CustomUserDetails currentUser) {
    	
    	System.out.println(boarddto.getTitle());
    	System.out.println(boarddto.getContent());
    	System.out.println(currentUser.getUser().getId());
    	
    	boardDto.saveBoard boardInfo = boardDto.saveBoard.builder()
    		.accountId(currentUser.getUser().getId())
    		.title(boarddto.getTitle())
    		.content(boarddto.getContent())
    		.build();
    		
    	boardService.saveBoard(boardInfo, currentUser.getUser());
    	
    	
    	return "redirect:/boardList";
    }

   

}

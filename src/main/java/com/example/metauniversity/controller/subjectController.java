package com.example.metauniversity.controller;

import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.subjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class subjectController {

    private final subjectService subjectService;

    /**
     * 수강목록 / 내가 신청한 목록 조회
     */
    @GetMapping("/sugang")
    public String gotoSugang(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {

        model.addAttribute("subjectData", subjectService.getAll());
        model.addAttribute("mySubjectData", subjectService.getMySubject(currentUser.getUser()));

        return "index.html";
    }

    /**
     * 수업 등록(관리자)
     */

    /**
     * 수업 수정
     */

    /**
     * 수업 삭제
     */
}

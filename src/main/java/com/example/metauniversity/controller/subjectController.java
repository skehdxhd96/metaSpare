package com.example.metauniversity.controller;

import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.subjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class subjectController {

    private final subjectService subjectService;

    /**
     * 수강목록 / 내가 신청한 목록 조회
     */
    @GetMapping("/schedule")
    public String gotoSugang(Model model
            ,@AuthenticationPrincipal CustomUserDetails currentUser) {

        model.addAttribute("subjectDatas", subjectService.getAll());
        model.addAttribute("mySubjectData", subjectService.getMySubject(currentUser.getUser()));

        return "scheduleManagement.html";
    }

    /**
     * 수업 등록(관리자) POST
     */
    @PostMapping("/schedule/create")
    public String createSubjectPOST(subjectDto.create createDto,
                                @AuthenticationPrincipal CustomUserDetails currentUser) {
        subjectService.addSubject(createDto, currentUser.getUser());
        return "redirect:/schedule/create";
    }

    /**
     * 수업 등록(관리자) GET
     */
    @GetMapping("/schedule/create")
    public String createSubjectGET(Model model, subjectDto.search searchDto
            ,@AuthenticationPrincipal CustomUserDetails currentUser) {

        model.addAttribute("subjectDatas", subjectService.getAllBySearch(searchDto));
        model.addAttribute("user",
                new subjectDto.userdata(currentUser.getUser().getId(), currentUser.getUsername()));

        return "scheduleAdd";
    }

    /**
     * 수업 수정
     */

    /**
     * 수업 삭제
     */
}

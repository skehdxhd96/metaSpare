package com.example.metauniversity.controller;

import com.example.metauniversity.domain.subject.dto.subjectData;
import com.example.metauniversity.domain.subject.dto.subjectDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.subjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class subjectRestController {

    private final subjectService subjectService;

    /**
     * 수강신청
     */
    @PostMapping("/subject/enroll/{subjectId}")
    public subjectDto.enroll subjectEnroll(@PathVariable Long subjectId,
                                           @AuthenticationPrincipal CustomUserDetails currentUser) {
        return subjectService.EnrollSubject(subjectId, currentUser.getUser());
    }

    /**
     * 수강취소
     */
    @PostMapping("/subject/cancel/{subjectId}")
    public subjectDto.enroll subjectCancel(@PathVariable Long subjectId,
                                           @AuthenticationPrincipal CustomUserDetails currentUser) {
        return subjectService.cancelSubject(subjectId, currentUser.getUser().getId());
    }

    /**
     * 수업 검색 필터링
     */
    @GetMapping("/subject/list/search")
    public subjectData<List<subjectDto.getList>> getAllSubjectBySearch(@ModelAttribute subjectDto.search searchDto) {
        List<subjectDto.getList> slist = subjectService.getAllBySearch(searchDto);
        return new subjectData<>(slist.size(), slist);
    }
}

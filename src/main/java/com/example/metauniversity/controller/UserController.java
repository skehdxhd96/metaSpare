package com.example.metauniversity.controller;

import com.example.metauniversity.domain.User.EnrollmentStatus;
import com.example.metauniversity.domain.User.dto.userDto;
import com.example.metauniversity.security.CustomUserDetails;
import com.example.metauniversity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public String signIn(@ModelAttribute userDto.signIn signindto) {
        userService.saveUser(signindto);
        return "redirect:/home";
    }

    /**
     * 내 정보 조회
     */
    @GetMapping("/user/info")
    public String getMyInfo(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        model.addAttribute("userData", userService.getUserInfo(currentUser.getUser().getId()));
        return "/info";
    }

    /**
     * 내 정보 수정(파일업로드)
     */
    @PostMapping("/user/info/modify")
    public String updateMyInfo(@AuthenticationPrincipal CustomUserDetails currentUser,
                               @ModelAttribute userDto.update updateDto) {

        userService.updateMyInfo(updateDto, currentUser.getUser());

        return "redirect:/user/info";
    }

    /**
     * 휴학, 복학 신청
     */
    @PostMapping("/user/enrollChange")
    public String applyLeave(EnrollmentStatus enrollmentStatus,
                             @AuthenticationPrincipal CustomUserDetails currentUser) {

        userService.applyLeave(currentUser.getUser().getId(), enrollmentStatus);

        return "redirect:/user/info";
    }
}

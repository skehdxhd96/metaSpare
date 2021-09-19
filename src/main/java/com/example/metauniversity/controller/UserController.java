package com.example.metauniversity.controller;

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
}

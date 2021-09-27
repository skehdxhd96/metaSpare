package com.example.metauniversity.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    /**
     * 홈화면
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 회원가입 페이지
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 에러페이지
     */
    @GetMapping("/denied")
    public String denied() {
        return "error";
    }


}
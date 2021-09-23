package com.example.metauniversity.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/info")
    public String hello() {
        return "info";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/denied")
    public String denied() {
        return "error";
    }
    @GetMapping("/boardList")
    public String boardList(){
        return "boardList";
    }

}
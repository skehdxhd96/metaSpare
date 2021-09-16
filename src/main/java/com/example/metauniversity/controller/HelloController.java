package com.example.metauniversity.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
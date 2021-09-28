package com.example.metauniversity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test2Controller {

    @GetMapping("/studentList")
    public String students() {

        return "studentsList";
    }
}

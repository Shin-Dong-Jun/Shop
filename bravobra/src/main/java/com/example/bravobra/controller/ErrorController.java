package com.example.bravobra.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/errorAccess")
    public String errorAccess() {
        return "error/errorAccess";
    }
}

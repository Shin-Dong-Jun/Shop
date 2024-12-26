package com.example.bravobra.controller;

import com.example.bravobra.exception.DuplicateMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    public String handleDuplicateMemberException(DuplicateMemberException e, Model model) {
        // 에러 메시지를 모델에 담아 뷰로 전달
        model.addAttribute("error", e.getMessage());
        return "/exception/duplicateMember"; // error페이지로 전달.
    }

}

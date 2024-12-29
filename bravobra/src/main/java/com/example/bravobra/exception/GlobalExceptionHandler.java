package com.example.bravobra.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMemberException.class)
    public String handleDuplicateMemberException(DuplicateMemberException e, Model model) {
        // 에러 메시지를 모델에 담아 뷰로 전달
        model.addAttribute("error", e.getMessage());
        return "/exception/duplicateMember"; // error페이지로 전달.
    }

}

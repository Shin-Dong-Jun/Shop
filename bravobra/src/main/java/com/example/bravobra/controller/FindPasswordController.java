package com.example.bravobra.controller;

import com.example.bravobra.dto.request.FindPasswordDto;
import com.example.bravobra.dto.request.resetPasswordDto;
import com.example.bravobra.service.PasswordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class FindPasswordController {

    private final PasswordService passwordService;

    @GetMapping("/find-password")
    public String showFindPasswordForm(FindPasswordDto findPwDto, Model model) {
        model.addAttribute("findPasswordDto", findPwDto);
        return "login/findPasswordForm";
    }

    @PostMapping("/find-password")
    public String findPassword(@Valid FindPasswordDto findPwDto, BindingResult result, Model model) {

        // 입력 도중에 에러가생기면 다시 폼으로 이동,
        if(result.hasErrors()){
            return "login/findPasswordForm";
        }
        // 에러가 없으면 회원이 있는지 부터 확인
        try{
            String email = passwordService.verifyMember(findPwDto);
            model.addAttribute("email", email);
            return "login/resetPasswordForm";
        }catch(NoSuchElementException e){
            result.reject("globalError", "입력하신 정보와 일치하는 회원이 없습니다.");
            return "login/findPasswordForm";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@Valid resetPasswordDto resetPwDto, BindingResult result, Model model){

        if(result.hasErrors()){
            result.reject("error", "비밀번호를 형식에 맞춰 입력해주세요.");
            return "login/resetPasswordForm";
        }

        // 비밀번호 일치 여부 확인
        if(!resetPwDto.getNewPassword().equals(resetPwDto.getNewPasswordConfirm())){
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login/resetPasswordForm";
        }

        try{
            passwordService.updatePassword(resetPwDto.getEmail(), resetPwDto.getNewPassword());
            model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
            return "login/resetSuccess";
        }catch(IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "login/resetPasswordForm";
        }

    }

}

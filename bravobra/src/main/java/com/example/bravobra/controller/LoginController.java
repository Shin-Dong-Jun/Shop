package com.example.bravobra.controller;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.LoginDto;
import com.example.bravobra.service.LoginService;
import com.example.bravobra.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;


    // Login 화면 호출
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto, BindingResult result, String redirectURL
                        , HttpServletRequest request) throws Exception {
        //1. id, pw 확인
        if(result.hasErrors()) {
            return "login";
        }
        Member loginMember = loginService.login(loginDto.getEmail(), loginDto.getPassword());
        // loginMember가 null이면 로그인 실패
        if(loginMember == null){
            result.reject("로그인에 실패하였습니다.", "이메일 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }
        // null이 아니면
        String memberName = loginMember.getEmail();
        HttpSession session = request.getSession();
        session.setAttribute("loginEmail", loginDto.getEmail());
        session.setAttribute("memberName", memberName);

        if (redirectURL == null || redirectURL.isEmpty()) {
            redirectURL = "/";
        }

        return "redirect:" + redirectURL;
    }
        /*
        result.reject(String errorCode)
         */

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}

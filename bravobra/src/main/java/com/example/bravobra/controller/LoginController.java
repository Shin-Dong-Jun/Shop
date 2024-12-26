package com.example.bravobra.controller;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.LoginDto;
import com.example.bravobra.dto.request.FindIdDto;
import com.example.bravobra.service.LoginService;
import com.example.bravobra.service.MemberService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // Login 화면 호출
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1. 유효성 검사
        if (result.hasErrors()) {
            return "login";
        }
        //2. 로그인 시도
        Member loginMember = loginService.login(loginDto.getEmail(), loginDto.getPassword());
        if (loginMember == null) {
            result.reject("loginFail", "이메일 또는 비밀번호가 맞지 않습니다.");
            return "login"; // 실패 시 로그인 페이지로 이동
        }

        // 2. 세션 생성
        HttpSession session = request.getSession(); // 세션 생성 true, false
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember); //

        log.info("로그인 성공 : 세션 생성, 세션 ID: {}", session.getId());
        log.info("세션 속성: {}", session.getAttribute(SessionConst.LOGIN_MEMBER));
        log.info("비밀번호: {}", loginMember.getPassword());

        //3. 쿠키 생성
        Cookie loginCookie = new Cookie("loginMember", URLEncoder.encode(loginMember.getId().toString(), "UTF-8")); // getid만 가져오게
        loginCookie.setHttpOnly(true);
        loginCookie.setPath("/");
        loginCookie.setMaxAge(60 * 60);

        //4. 응답할 때 쿠키 추가.
        response.addCookie(loginCookie);

        return "redirect:/";
    }
        /*
        result.reject(String errorCode)
         */

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // invalidate 세션 제거하는 메서드.
        }

        //쿠키 삭제
        Cookie loginCookie = new Cookie("loginMember", null);
        loginCookie.setMaxAge(0);
        loginCookie.setPath("/");
        response.addCookie(loginCookie);

        log.info("로그아웃: 세션 및 쿠키 삭제 완료");
        return "redirect:/";
    }

    @GetMapping("/find-id")
    public String showFindIdForm() {
        return "login/findIdForm";
    }

    @PostMapping("/find-id")
    public String findId(@Valid FindIdDto findIdDto, BindingResult result, HttpServletRequest request, HttpServletResponse response, Model model
    ) throws Exception {


        //1. 유효성 검사.
        if (result.hasErrors()) {
            return "findIdForm";
        }

        //2. 이메일이 있는지 확인하기 위해서 전화번호랑 이름을 입력받음.
        String memberId = loginService.findEmailByPhoneAndName(findIdDto.getPhoneNumber(), findIdDto.getName());

        //3. 일치하는 이메일이 없으면.
        if (memberId == null) {
            result.reject("findIdFail", "입력하신 정보와 일치하는 이메일이 없습니다.");
            return "login/findIdForm";
        }

        //4. 일치하는 이메일이 있으면 아이디를 보여주는 창으로 넘어가야 함.
        model.addAttribute("findSuccess", memberId);

        return "login/findIdSuccess";
    }
}

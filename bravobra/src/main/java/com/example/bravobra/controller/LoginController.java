package com.example.bravobra.controller;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.LoginDto;
import com.example.bravobra.service.LoginService;
import com.example.bravobra.service.MemberService;
import com.example.bravobra.session.SessionConst;
import com.example.bravobra.session.SessionManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.util.UUID;

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
    public String login(@Valid LoginDto loginDto, BindingResult result, String redirectURL
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        if (redirectURL == null || redirectURL.isEmpty()) {
            redirectURL = "/";
        }
        String msg = URLEncoder.encode(loginMember.toString(), "UTF-8");

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
}

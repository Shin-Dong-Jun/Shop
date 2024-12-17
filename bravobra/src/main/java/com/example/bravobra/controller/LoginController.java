package com.example.bravobra.controller;

import com.example.bravobra.dto.LoginMemberResponse;
import com.example.bravobra.dto.request.LoginDtoRequest;
import com.example.bravobra.dto.request.SignUpDtoRequest;
import com.example.bravobra.repository.LoginRepository;
import com.example.bravobra.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {
    private final LoginService loginService;

    @GetMapping
    public String main(){
        return "main";
    }

    //로그인
    @PostMapping("/login")
    public String Login(@RequestBody LoginDtoRequest logindto, HttpServletRequest request , Model model){
        LoginMemberResponse member = loginService.loginMember(logindto);
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30);
        }
        출처: https://congsong.tistory.com/38 [Let's develop:티스토리]
        return "login";
    }


    //로그아웃
    @PostMapping("/logout")
    public String Logout(HttpServletRequest request){

        //세션을 삭제
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    //회원가입
    @PostMapping("/signup")
    public String SignUp(@Valid @RequestBody SignUpDtoRequest request, Model model){
        loginService.singup(request);
        return "redirect:/";
    }

}

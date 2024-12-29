package com.example.bravobra.controller;


import com.example.bravobra.domain.Member;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String main(HttpSession session, Model model) {
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginMember !=null);
        return "index"; // main 화면
    }
}

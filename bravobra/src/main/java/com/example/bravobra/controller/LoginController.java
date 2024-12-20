package com.example.bravobra.controller;


import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;

@Controller
@RequestMapping("/members")
public class LoginController {


    @GetMapping("/login")
    public String showLogin(){
        return "login"; //
    }
    @PostMapping("/login")
    public String login(String id, String pwd, Model model) throws Exception{
        //1. id, pw 확인

        if(loginCheck(id, pwd)){
            model.addAttribute("id", id);
            model.addAttribute("pwd", pwd);
            return "userInfo"; //userInfo.html
        }else {
            //2, 일치하면 userinfo.html을 보여주고
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/members/login?msg="+msg; // GET URL 뒤에다가 쿼리스트링 URL 다시쓰기.
        }

    }
    private boolean loginCheck(String id, String pwd) {
        return id.equals("asdf") && pwd.equals("1234");
    }
}

package com.example.bravobra.controller;


import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/members/list")
    public String showMemberList(Model model) {
        List<MemberDto> members = memberService.findAllMemberDto();
        model.addAttribute("members", members);
        return "admin/memberListForm";
    }
    
}

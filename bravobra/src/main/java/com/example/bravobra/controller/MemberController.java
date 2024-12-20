package com.example.bravobra.controller;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/join")
    public String createForm(Model model) {
        model.addAttribute("memberDto", MemberDto.builder().build());
        return "members/createMemberForm";
    }

    @PostMapping("/join")
    public String create(@Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        memberService.join(memberDto);


        return "redirect:/";
    }

//    @GetMapping("/list")
//    public String list(Model model){
//        List<Member> members = memberService.findAllMember();
//        model.addAttribute("members", members); // 담은 다음 Form으로 전달.
//        return "members/MemberListForm";
//    }

    @GetMapping("/list") // dto로 받는 것이 좋음. entity에 직접 접근하는 것은 좋지 않음.
    public String list(Model model) {
        List<MemberDto> members = memberService.findAllMemberDto();
        model.addAttribute("members", members); // 담은 다음 Form으로 전달.
        return "members/MemberListForm";
    }


}

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
        // 1. model에 MemberDto객체를 생성해서 보냄.(빈객체)
        model.addAttribute("memberDto", MemberDto.builder().build());
        return "members/createMemberForm"; // 2. createMemberForm을 화면에 보여줌.
    }

    @PostMapping("/join")
    public String create(@Valid @ModelAttribute("memberDto")MemberDto memberDto,
                         BindingResult result,
                         Model model) { // @Valid로 Dto에 할당한 값의 유효성 검사.
        // result에 오류 정보를 저장. // hasErrors() - 바인딩과정에서 오류가 발생했는지 확인.

        // 1. 오류가 발생했으면 다시 회원가입화면으로 이동.
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        //2. 오류가 발생하지 않았으면 이메일 중복체크(Service단 처리)
        memberService.join(memberDto);
        return "redirect:/";
    }

    @GetMapping("/list") // dto로 받는 것이 좋음. entity에 직접 접근하는 것은 좋지 않음.
    public String list(Model model) {
        List<MemberDto> members = memberService.findAllMemberDto();
        model.addAttribute("members", members); // 담은 다음 Form으로 전달.
        return "members/MemberListForm";
    }
}

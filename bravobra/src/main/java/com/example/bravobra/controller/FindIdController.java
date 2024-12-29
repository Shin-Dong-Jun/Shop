package com.example.bravobra.controller;

import com.example.bravobra.dto.request.FindIdDto;
import com.example.bravobra.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class FindIdController {

    private final LoginService loginService;

    @GetMapping("/find-id")
    public String showFindIdForm(FindIdDto findIdDto, Model model) {
        model.addAttribute("findIdDto", findIdDto);

        return "login/findIdForm";
    }

    @PostMapping("/find-id")
    public String findId(@Valid FindIdDto findIdDto, BindingResult result, Model model
    ) throws Exception {

        //1. 유효성 검사.
        if (result.hasErrors()) {
            model.addAttribute("findIdForm", findIdDto); // 입력한 데이터 유지.
            return "login/findIdForm";
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

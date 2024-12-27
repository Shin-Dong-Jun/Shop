package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import com.example.bravobra.dto.PageHandler;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.entity.Product;
import com.example.bravobra.service.FaqService;
import com.example.bravobra.service.MemberService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
//화면요청에서 받아서 서비스에게 줘야함
@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {


    private final FaqService faqService;
    private final MemberService memberService;


    //글목록 페이지
    @GetMapping
    public String faq(Model model) {
        List<Faq> faqlist = faqService.getAllFaq();
        model.addAttribute("faqList", faqlist);
        return "faq/faq";
    }


    // 글쓰기 페이지
    @GetMapping("/write")
    public String getWritePage() {
        return "faq/write";
    }



    //1.게시글 등록
    @PostMapping("/post")
    public String postFaq(@ModelAttribute RequestFaqDto requestFaqDto, Model model, HttpServletRequest request, Errors errors) {
        //memberId = 1l;

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);


        Long memberId = member.getId();
        log.info(memberId.toString());

        MemberType memberType = member.getMemberType();

        if (member == null) {
            return "redirect:/";
        }

//        if(memberType != MemberType.ADMIN) {
//            model.addAttribute("errorMessage","관리자만 가능합니다");
//            return "redirect:/faq/list";
//        }

       faqService.postFaq(requestFaqDto , memberId);
        return "redirect:/faq/list";
    }


    //2.전체조회
    @GetMapping("/list")
    public String getAllFaq(Model model, @RequestParam(required = false, defaultValue = "1", value = "page") int pageNo,
                            @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") String criteria) {
        System.out.println("list");
        System.out.println(model);
        List<Faq> faqlist = faqService.getAllFaq();

        model.addAttribute("faqlist", faqlist);
        return "/faq/faq";
    }


    //3.단일 조회
    @GetMapping("/get/{faqId}")
    public String getFaq(@PathVariable Long faqId, Model model) {
        faqService.getFaqById(faqId);
        model.addAttribute("faq", faqService.getFaqById(faqId));
        return "/faq/get";
    }


    //4.검색 조회
    @GetMapping("/search")
    public String searchFaq(@RequestParam String title, Model model) {
        List<Faq> faqList = faqService.findByTitle(title);
        model.addAttribute("faqlist", faqList);
        return "/faq/faq";
    }



    //5.삭제
    @PostMapping("/remove/{faqId}")
    public String removeFaq(@PathVariable Long faqId,  Model model) {

//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//
//        MemberType memberType = member.getMemberType();


//        if(memberType != MemberType.ADMIN) {
//            model.addAttribute("errorMessage","관리자만 가능합니다");
//            return "redirect:/faq/list";
//        }


        faqService.removeFaq(faqId);
        return "redirect:/faq/list";
    }


    //6.수정
    @PostMapping("/modify/{faqId}")
    public String modifyFaq(@ModelAttribute RequestFaqDto requestFaqDto, @PathVariable Long faqId) {
        faqService.modifyFaq(requestFaqDto, faqId);
        return "redirect:/faq/list";
    }


    //6-1 수정 폼
    @GetMapping("/modify/{faqId}")
    public String modifyFaqForm(@PathVariable Long faqId, Model model) {

//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        MemberType memberType = member.getMemberType();
//
//        if(memberType != MemberType.ADMIN) {
//            model.addAttribute("errorMessage","관리자만 가능합니다");
//            return "redirect:/faq/list";
//        }

            Faq faq = faqService.getFaqById(faqId);
            model.addAttribute("faq", faq);
        return "/faq/modify";
    }


}

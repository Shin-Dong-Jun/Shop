package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.service.FaqService;
import com.example.bravobra.service.MemberService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//화면요청에서 받아서 서비스에게 줘야함
@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {


    private final FaqService faqService;
    private final MemberService memberService;


    //글목록 페이지
    @GetMapping
    //dto로 변환
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
    public String postFaq(@ModelAttribute RequestFaqDto requestFaqDto, HttpServletRequest request, Long memberId) {
        memberId =1234l;
       faqService.postFaq(requestFaqDto , memberId);
        return "redirect:/faq/list";
    }


    //2.전체조회
    @GetMapping("/list")
    public String getAllFaq(Model model) {
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
    public String removeFaq(@PathVariable Long faqId, HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute("memberId");
        String type = (String) httpSession.getAttribute("type");

        try {
            if (!type.equals("admin")) {
                throw new Exception("관리자만 접근할 수 있습니다.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

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
    public String modifyFaqForm(@PathVariable Long faqId, Model model, HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute("memberId");
        String type = (String) httpSession.getAttribute("type");

        try {
            if (!type.equals("admin")) {
                throw new Exception("관리자만 접근할 수 있습니다.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

            Faq faq = faqService.getFaqById(faqId);
            model.addAttribute("faq", faq);
        return "/faq/modify";
    }


}

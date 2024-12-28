package com.example.bravobra.controller;

import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.entity.Product;
import com.example.bravobra.service.FaqService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.bravobra.entity.QFaq.faq;

//화면요청에서 받아서 서비스에게 줘야함
@Controller
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

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
    public String getWritePage(Model model, HttpSession session, RequestFaqDto requestFaqDto) {
        model.addAttribute("faq", requestFaqDto);
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginMember);

        return "faq/write";
    }

    //1.게시글 등록
    @PostMapping("/post")
    public String postFaq(@ModelAttribute RequestFaqDto requestFaqDto, HttpSession httpSession, Model model) {
        Long memberId = 1234L;

        Faq faq = faqService.postFaq(requestFaqDto, memberId);
        System.out.println("Received memberId: " + memberId);
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
    public String removeFaq(@PathVariable Long faqId) {
        faqService.removeFaq(faqId);
        return "redirect:/faq/list";
    }

    //6. 수정 폼
    @GetMapping("/modify")
    public String modifyFaqForm(Long faqId, Model model) {
        Faq faq = faqService.getFaqById(faqId);
        model.addAttribute("faq", faq);
        return "/faq/modify";
    }

    //6. 수정
    @PostMapping("/modify")
    public String modifyFaq(@ModelAttribute RequestFaqDto requestFaqDto,Long faqId) {
        faqService.modifyFaq(requestFaqDto, faqId);
        return "redirect:/faq/list";
    }
}
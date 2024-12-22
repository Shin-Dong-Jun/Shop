package com.example.bravobra.controller;

import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.service.FaqService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

    //글목록 페이지
    @GetMapping
    public String faq(Model model) {
        List<Faq> faqlist = faqService.getAllFaq();
        model.addAttribute("faqlist", faqlist);
        return "faq";
    }

    // 글쓰기 페이지
    @GetMapping("/post")
    public String getWritePage() {
        return "write";
    }


    //1.게시글 등록
    @PostMapping("/post")
    public String postFaq(@ModelAttribute @Valid RequestFaqDto requestFaqDto, HttpSession httpSession) {
         Long memberId = (Long) httpSession.getAttribute("memberId");
         String nickname = (String) httpSession.getAttribute("nickname");
         faqService.postFaq(requestFaqDto, memberId, nickname);
        return "redirect:/faq";
    }


    //2.전체조회
    @GetMapping("/allview")
    public List<Faq> getAllFaq() {
        return faqService.getAllFaq();
    }


    //3.부분조회(상세보기)
    @GetMapping("/{memberId}")
    public Faq getFaq(@PathVariable Long memberId) {
        return faqService.getFaq(memberId);
    }


    //4.삭제
    @DeleteMapping("/remove/{faqId}")
    public void removeFaq(@PathVariable Long faqId) {
        faqService.removeFaq(faqId);
    }



//    //5.수정
//    public void modifyFaq(@ModelAttribute @Valid RequestFaqDto requestFaqDto){
//        faqService.modifyFaq(requestFaqDto);
//    }

}

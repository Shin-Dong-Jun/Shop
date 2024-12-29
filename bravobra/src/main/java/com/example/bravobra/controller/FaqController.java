package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.service.FaqService;
import com.example.bravobra.service.MemberService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//화면요청에서 받아서 서비스에게 줘야함
@Slf4j
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

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);


        Long memberId = member.getId();

        log.info(memberId.toString());

        MemberType memberType = member.getMemberType();

        if (member == null) {
            return "redirect:/";
        }


       faqService.postFaq(requestFaqDto , memberId);
        return "redirect:/faq/list";
    }


    //2. 전체조회
    @GetMapping("/list")
    public String getAllNotice(Model model, HttpServletRequest request,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 로그인 여부 확인
        if (member != null) {
            // 로그인한 사용자의 타입 확인
            MemberType memberType = member.getMemberType();
            if (memberType != MemberType.ADMIN) {
                model.addAttribute("isAdmin", false);
            } else {
                model.addAttribute("isAdmin", true);
            }
        } else {
            // 로그인하지 않은 경우, 관리자 정보 관련 처리 생략
            model.addAttribute("isAdmin", false);
        }

        // 로그인하지 않아도 공지사항을 조회할 수 있도록
        Page<Faq> faqPage = faqService.getFaqWithPagination(page, size);
        // 페이지네이션 정보 추가
        model.addAttribute("faqlist", faqPage.getContent());  // 페이지에 맞는 공지사항 리스트
        model.addAttribute("currentPage", page);  // 현재 페이지 번호
        model.addAttribute("totalPages", faqPage.getTotalPages());  // 전체 페이지 수
        model.addAttribute("totalItems", faqPage.getTotalElements());  // 전체 아이템 수
        model.addAttribute("pageSize", size);  // 페이지 크기

        return "/faq/faq";  // 공지사항 게시판 뷰
    }



    //3.단일 조회
    @GetMapping("/get/{faqId}")
    public String getFaq(@PathVariable Long faqId, Model model) {
       Faq faq =  faqService.getFaqById(faqId);
        String contentWithBr = faq.getContent().replace("\n", "<br>");
        model.addAttribute("contentWithBr", contentWithBr);
        model.addAttribute("faq", faq);
        return "/faq/get";
    }



    //4.검색 조회
    @GetMapping("/search")
    public String searchFaq(@RequestParam String title, Model model) {
        List<Faq> faqList = faqService.findByTitle(title);
        model.addAttribute("faqlist", faqList);
        return "/faq/faq";
    }



    //4-1.예외담기
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "faq/faq";
    }



    //5.삭제
    @PostMapping("/remove/{faqId}")
    public String removeFaq(@PathVariable Long faqId) {
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

            Faq faq = faqService.getFaqById(faqId);
            model.addAttribute("faq", faq);
        return "/faq/modify";
    }

}

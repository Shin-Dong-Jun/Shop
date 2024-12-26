package com.example.bravobra.controller;


import com.example.bravobra.dto.request.RequestNoticeDto;
import com.example.bravobra.entity.Notice;
import com.example.bravobra.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    //글목록 페이지
    @GetMapping
    public String notice(Model model) {
        List<Notice> noticeList = noticeService.getAllNotice();
        model.addAttribute("noticelist", noticeList);
        return "notice/notice";
    }


    // 글쓰기 페이지
    @GetMapping("/post")
    public String getWritePage() {
        return "notice/write";
    }


    //1.게시글 등록
    @PostMapping("/post")
    public String postNotice(@ModelAttribute RequestNoticeDto requestNoticeDto, HttpSession httpSession) {

        Long memberId = (Long) httpSession.getAttribute("memberId");
        String type = (String) httpSession.getAttribute("type");


        try {
            if (!type.equals("admin")) {
                throw new Exception("관리자만 접근할 수 있습니다.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        noticeService.postNotice(requestNoticeDto, memberId);
        return "redirect:/notice";
    }


    //2.전체조회
    @GetMapping("/list")
    public String getAllNotice(Model model) {
        List<Notice> noticelist = noticeService.getAllNotice();
        model.addAttribute("noticelist", noticelist);
        return "/notice/notice";
    }


    //3.단일 조회
    @GetMapping("/get/{noticeId}")
    public String getNotice(@PathVariable Long noticeId) {
        noticeService.getNoticeById(noticeId);
        return "/notice/notice";

    }


    //4.검색 조회
    @GetMapping("/search")
    public String searchNotice(@RequestParam String title, Model model) {
        List<Notice> notices = noticeService.findByTitle(title);
        model.addAttribute("notices", notices);
        return "/notice/notice";

    }

    //5.삭제
    @DeleteMapping("/remove/{noticeId}")
    public void removeNotice(@PathVariable Long noticeId) {
        noticeService.removeNotice(noticeId);
    }


    //6.수정
    @PatchMapping("/modify/{noticeId}")
    public void modifyFaq(@ModelAttribute RequestNoticeDto requestNoticeDto, @PathVariable Long noticeId) {
        noticeService.modifyFaq(requestNoticeDto, noticeId);
    }


}

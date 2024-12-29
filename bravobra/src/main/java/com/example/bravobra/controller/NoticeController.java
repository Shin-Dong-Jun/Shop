package com.example.bravobra.controller;
import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import com.example.bravobra.dto.request.RequestNoticeDto;
import com.example.bravobra.entity.Notice;
import com.example.bravobra.service.NoticeService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
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
    @GetMapping("/write")
    public String getWritePage( Model model) {
        return "notice/write";
    }


    //1.게시글 등록
    @PostMapping("/post")
    public String postNotice(@ModelAttribute RequestNoticeDto requestNoticeDto, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();


        noticeService.postNotice(requestNoticeDto, memberId);
        return "redirect:/notice/list";
    }


    //2.전체조회
    @GetMapping("/list")
    public String getAllNotice(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);


        if(member != null) {
            MemberType memberType = member.getMemberType();
            if (memberType != MemberType.ADMIN) {
                model.addAttribute("isAdmin", false);
            } else {
                model.addAttribute("isAdmin", true);
            }
        } else if(member == null) {
            return "redirect:/notice";
        }



        List<Notice> noticelist = noticeService.getAllNotice();
        model.addAttribute("noticelist", noticelist);
        return "/notice/notice";
    }


    //3.단일 조회
    @GetMapping("/get/{noticeId}")
    public String getNotice(@PathVariable Long noticeId, Model model) {
        Notice notice = noticeService.getNoticeById(noticeId);
        String contentWithBr = notice.getContent().replace("\n", "<br>");
        model.addAttribute("contentWithBr", contentWithBr);
        model.addAttribute("notice", notice);
        return "/notice/get";
    }



    //4.검색 조회
    @GetMapping("/search")
    public String searchNotice(@RequestParam String title, Model model) {
        List<Notice> noticelist = noticeService.findByTitle(title);
        model.addAttribute("noticelist", noticelist);
        return "notice/notice";

    }


    //4-1 검색 예외.
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "notice/notice";
    }


    //5.삭제
    @PostMapping("/remove/{noticeId}")
    public String removeNotice(@PathVariable Long noticeId) {

        noticeService.removeNotice(noticeId);
        return "redirect:/notice/list";
    }


//    //6.수정 (긴급)
//    @PostMapping("/urgency/{noticeId}")
//    public String urgencyNotice( @PathVariable Long noticeId) {
//        Notice updatedNotice = noticeService.urgencyNotice(noticeId);
//
//        return "redirect:/notice/list";
//    }


    //7.긴급



    //6-1 삭제 폼
    @GetMapping("/controll/{noticeId}")
    public String modifyNoticeForm(@PathVariable Long noticeId, Model model) {

//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//
//        MemberType memberType = member.getMemberType();
//
//        if(memberType != MemberType.ADMIN) {
//            model.addAttribute("errorMessage","관리자만 가능합니다");
//            return "redirect:/notice/list";
//        }

        Notice notice = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice", notice);

        return "/notice/controll";


    }

}

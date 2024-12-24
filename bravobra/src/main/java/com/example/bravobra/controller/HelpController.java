package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.help.Help;
import com.example.bravobra.dto.CreateHelpDto;
import com.example.bravobra.dto.UpdateHelpDto;
import com.example.bravobra.service.HelpService;
import com.example.bravobra.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/help")
public class HelpController {

   private final HelpService helpService;
   private final MemberService memberService;

   /**
    * 문의 작성하기 페이지 이동
    * @author sr
    * @param model
    */
   @GetMapping("/add")
   public String showHelpForm(Model model) {
      //TODO: Session으로 수정하기
      /*Member member = (Member) session.getAttribute("loggedInMember");
      if (member == null) {
         // 로그인된 사용자가 없으면 로그인 페이지로 리다이렉트
         return "redirect:/login";
      }*/

      //임의 설정
      String memberEmail = "growth207@naver.com";
      Member member = memberService.findOne(memberEmail);

      CreateHelpDto createHelpDto = CreateHelpDto.builder()
              .memberId(member.getId())
              .nickname(member.getNickName())
              .build();

      model.addAttribute("createHelpDto", createHelpDto);
      return "help/addHelp";
   }

   /**
    * 문의 작성하기
    * @author sr
    * @param createHelpDto
    * @param result
    */
   @PostMapping("/add")
   public String addHelp(@Valid CreateHelpDto createHelpDto, BindingResult result) {
      if (result.hasErrors()) {
         return "help/addHelp";
      }

      helpService.addHelp(createHelpDto);
      return "redirect:/help";
   }

   /**
    * 문의 게시판 목록 가져오기
    * @author sr
    * @param model
    */
   @GetMapping
   public String getHelpList(Model model) {
      //TODO : 엔티티타입 쓰지말고 DTO를 써서 수정
      List<Help> helpList = helpService.getHelpList();
      model.addAttribute("helpList", helpList);

      return "help/getHelpList";
   }

   /**
    * 문의 글 상세보기
    * @author sr
    * @param helpId
    * @param model
    * @return
    */
   @GetMapping("/detail/{helpId}")
   public String getHelp(@PathVariable Long helpId, Model model) {
      Help help = helpService.getHelp(helpId);
      helpService.increaseViewCnt(helpId);
      model.addAttribute("help", help);

      return "help/getHelp";
   }

   /**
    * 문의 글 수정하기
    * @author sr
    * @param helpId
    * @param updateHelpDto
    * @return
    */
   @PostMapping("/detail/{helpId}")
   public String editHelp(@PathVariable Long helpId, UpdateHelpDto updateHelpDto) {
      //TODO : 세션 사용자 ID = memberId 시에만 수정버튼 보이기
      helpService.editHelp(helpId, updateHelpDto);
      return "redirect:/help/detail/" + helpId;
   }

   @PostMapping("/{helpId}")
   public String deleteHelp(@PathVariable Long helpId){
      //TODO : 세션 사용자 ID = memberId 시에만 수정버튼 보이기
      helpService.deleteHelp(helpId);
      return "redirect:/help";
   }
}

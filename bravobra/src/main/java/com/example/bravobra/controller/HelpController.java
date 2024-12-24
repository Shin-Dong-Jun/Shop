package com.example.bravobra.controller;

import com.example.bravobra.domain.Help;
import com.example.bravobra.dto.CreateHelpDto;
import com.example.bravobra.service.HelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/help")
public class HelpController {

   private final HelpService helpService;

   @GetMapping("/create")
   public String showCreateHelpForm() {
      return "help/createForm";
   }

//   @PostMapping("/help")
//   public String writeHelp(@Valid CreateHelpDto helpDto) {
//      helpService.writeHelp(helpDto);
//      return "help/listHelp";
//   }
   @GetMapping
   public String getHelpList(Model model) {
      List<Help> helpList = helpService.getHelpList();
      model.addAttribute("helpList", helpList);

      return "help/getHelpList";
   }

   @GetMapping("/detail/{helpId}")
   public String getHelp(@PathVariable Long helpId, Model model) {
      Help help = helpService.getHelp(helpId);
      model.addAttribute("help", help);

      return "help/getHelp";
   }


}

package com.example.bravobra.service;

import com.example.bravobra.domain.Help;
import com.example.bravobra.repository.HelpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class HelpService {
   private final HelpRepository helpRepository;

//   public void writeHelp(CreateHelpDto helpDto) {
//      Help help = Help.toHelpEntity(helpDto);
//      helpRepository.save(help);
//   }

   public List<Help> getHelpList() {
      return helpRepository.findAll();
   }

   //예외처리...
   public Help getHelp(Long helpId) {
      return helpRepository.findByHelpId(helpId);
   }
}

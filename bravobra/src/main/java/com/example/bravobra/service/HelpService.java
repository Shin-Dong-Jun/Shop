package com.example.bravobra.service;

import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.help.Help;
import com.example.bravobra.dto.CreateHelpDto;
import com.example.bravobra.dto.UpdateHelpDto;
import com.example.bravobra.repository.HelpRepository;
import com.example.bravobra.repository.MemberRepository;
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
   private final MemberRepository memberRepository;

   public List<Help> getHelpList() {
      return helpRepository.findAll();
   }

   public Help getHelp(Long helpId) {
      Help help = helpRepository.findById(helpId)
              .orElseThrow(() -> new RuntimeException("해당 문의글이 존재하지 않습니다."));

      return help;
   }

   public void addHelp(CreateHelpDto createHelpDto) {
      Member member = memberRepository.findById(createHelpDto.getMemberId())
              .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + createHelpDto.getMemberId()));

      Help help = Help.toHelpEntity(createHelpDto, member);
      helpRepository.save(help);
   }

   public void increaseViewCnt(Long helpId) {
      Help help = helpRepository.findById(helpId)
              .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 존재하지 않습니다."));

      help.increaseViewCnt();
   }

   public void editHelp(Long id, UpdateHelpDto updateHelpDto) {
      Help help =  helpRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 존재하지 않습니다."));

      help.update(updateHelpDto.getTitle(), updateHelpDto.getContent());
   }

   public void deleteHelp(Long helpId) {
      Help help = helpRepository.findById(helpId)
              .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 존재하지 않습니다."));

      helpRepository.delete(help);
   }
}

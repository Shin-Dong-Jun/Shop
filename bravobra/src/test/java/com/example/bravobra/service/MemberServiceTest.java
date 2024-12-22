package com.example.bravobra.service;

import com.example.bravobra.domain.Member;
import com.example.bravobra.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Test
    void getMember() {

        Member build = Member.builder()
                .nickName("nickname")
                .password("password")
                .build();
        Member save = memberRepository.save(build);
        List<Member> allMember = memberService.findAllMember();

        Assertions.assertEquals(allMember.size(), 1);

    }


  
}
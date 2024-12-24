package com.example.bravobra.service;

import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
    }
    @Test
    void getMember() {

        Member build = Member.builder()
                .nickName("nickname")
                .password("password")
                .build();
        memberRepository.save(build);
        List<Member> allMember = memberService.findAllMember();

        Assertions.assertEquals(allMember.size(), 1);

    }


    @Test
    @DisplayName("비밀번호 암호화 테스트")
    public void testPasswordEncryption(){
        //given
        String rawPassword = "password";

        //when
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        //then
        Assertions.assertNotEquals(rawPassword, encodedPassword);
        Assertions.assertTrue(bCryptPasswordEncoder.matches(rawPassword, encodedPassword));

        System.out.println("암호화된 비밀번호: " + encodedPassword);
    }

    @Test
    @DisplayName("진짜 비밀번호 암호화 테스트")
    void testRealPasswordEncryption(){
        //given

        String rawPassword = "password";
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        Member member = Member.builder()
                .email("tlsehdwns147@naver.com")
                .password(encodedPassword)
                .nickName("fuck")
                .phoneNumber("010-5590-3697")
                .memberType(MemberType.MEMBER)
                .build();

        memberRepository.save(member);

        System.out.println(encodedPassword);
        System.out.println(member.getPassword());
        System.out.println(rawPassword);

        Assertions.assertTrue(bCryptPasswordEncoder.matches(rawPassword, member.getPassword()));
//        System.out.println("암호화된 비밀번호: " + memberRepository.findById(member.getId()).get().getPassword());
    }




  
}
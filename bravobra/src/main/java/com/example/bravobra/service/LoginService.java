package com.example.bravobra.service;


import com.example.bravobra.domain.Member;
import com.example.bravobra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    //1. 로그인 로직. 이메일이랑 비번이 DB에 있고 일치하면 member반환
    public Member login(String loginEmail, String password){
        return memberRepository.findByEmail(loginEmail).filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    /*
    filter : Optionial에 담긴 값이 특정 조건을 만족하는지 검사하는 메서드
    만족하면 Optional 반환
    아니면 null 반환
    findByEmail의 타입은 Optional<Member>!
     */
}
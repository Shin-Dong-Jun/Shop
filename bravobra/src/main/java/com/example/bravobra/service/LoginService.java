package com.example.bravobra.service;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.FindIdDto;
import com.example.bravobra.exception.DuplicateMemberException;
import com.example.bravobra.exception.InvalidLoginCredentialException;
import com.example.bravobra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //1. 로그인 로직. 이메일이랑 비번이 DB에 있고 일치하면 member반환
    public Member login(String loginEmail, String password) {
        // 이메일로 사용자 검색
        return memberRepository.findByEmail(loginEmail)
                .filter(member -> passwordEncoder.matches(password, member.getPassword())) // 비밀번호 확인
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.")); // 일치하지 않으면 null 반환
    }

    public String findEmailByPhoneAndName(String phoneNumber, String name){
        return memberRepository.findByPhoneNumberAndName(phoneNumber, name)
                .map(Member::getEmail)
                .orElse(null);
    }

    /*
    filter : Optionial에 담긴 값이 특정 조건을 만족하는지 검사하는 메서드
    만족하면 Optional 반환
    아니면 null 반환
    findByEmail의 타입은 Optional<Member>!
     */
}

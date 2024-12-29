package com.example.bravobra.service;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.FindPasswordDto;
import com.example.bravobra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;


    //회원이 있는지 없는지 확인. 이메일, 이름, 번호를 확인해서.
    public String verifyMember(FindPasswordDto findPwDto) {
        Optional<Member> member = memberRepository.findByEmailAndNameAndPhoneNumber(
                findPwDto.getEmail(),
                findPwDto.getName(),
                findPwDto.getPhoneNumber()
        );
        return member.map(Member::getEmail)
                .orElseThrow(() -> new NoSuchElementException("회원 정보가 일치하지 않습니다."));
    }

    @Transactional
    public void updatePassword(String email, String newPassword) {
        memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
        String encodedPassword = encoder.encode(newPassword);
        memberRepository.updatePassword(email, encodedPassword);
    }

}

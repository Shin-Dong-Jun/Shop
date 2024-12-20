package com.example.bravobra.service;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원 생성
    @Transactional
    public Long join(MemberDto memberDto) {

        validateDuplicateMember(memberDto.getEmail());

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .nickName(memberDto.getNickname())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
        System.out.println("member = " + member);
        memberRepository.save(member);
        return member.getId();

    }
    //중복체크 이메일
    private void validateDuplicateMember(String email) {
        if(memberRepository.existsByEmail(email)){
            throw new IllegalStateException("이미 등록된 이메일입니다");
        }
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }
    // 검색 기능 할 떄 필요 이름 한명 정학 때.
    public Member findOne(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("해당 이메일을 가진 회원이 존재하지 않습니다."));
    }

    public List<MemberDto> findAllMemberDto(){
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(member -> new MemberDto(
                        member.getEmail(),
                        member.getPassword(),
                        member.getNickName(),
                        member.getPhoneNumber(),
                        member.getRegisterDate()))
                .collect(Collectors.toList());
    }




}

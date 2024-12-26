package com.example.bravobra.service;


import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.exception.DuplicateMemberException;
import com.example.bravobra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder; // BCryptPasswordEncoder 추가



    // 회원 생성
    @Transactional
    public Long join(MemberDto memberDto) { // Controller에서 memberDto객체를 전달 받음.
        // 1. dto의 이메일 중복체크 메서드.
        validateDuplicateMember(memberDto.getEmail(), memberDto.getPhoneNumber());

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(memberDto.getPassword());


        // 2. 중복이 아니면 member에 정보 저장.
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(encryptedPassword)
                .nickName(memberDto.getNickname())
                .phoneNumber(memberDto.getPhoneNumber())
                .memberType(MemberType.MEMBER)
                .build();
        // 확인용
        System.out.println("member = " + member);

        // 3. 리포지터리에 저장.
        memberRepository.save(member);
        return member.getId(); // member id 리턴.( id를 왜 리턴한다고 적었지?)

    }

    //중복체크 이메일
    private void validateDuplicateMember(String email, String phoneNumber) {
        if(memberRepository.existsByEmail(email)){
            throw new DuplicateMemberException("이미 등록된 이메일입니다"); // 이미등록된 이메일일때 예외처리 해주어야함.
        }
        if(memberRepository.existsByPhoneNumber(phoneNumber)){
            throw new DuplicateMemberException("이미 등록된 휴대폰 번호입니다.");
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
                        member.getName(),
                        member.getPassword(),
                        member.getNickName(),
                        member.getPhoneNumber(),
                        member.getRegisterDate(),
                        member.getMemberType()))
                .collect(Collectors.toList());
    }




}

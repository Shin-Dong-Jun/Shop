package com.example.bravobra.config;


import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import com.example.bravobra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner { // 서버 실행할 때 확인후 관리자 계정 생성.

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;


    public void run(String... args) throws Exception {
        if(!memberRepository.existsByMemberType(MemberType.ADMIN)) {
            Member admin = Member.builder()
                    .email("admin@admin.com")
                    .password(encoder.encode("admin123!@"))
                    .nickname("Admin")
                    .memberType(MemberType.ADMIN)
                    .build();

            memberRepository.save(admin);
            System.out.println("관리자 아이디가 생성되었습니다.");
        }
    }
}

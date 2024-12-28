package com.example.bravobra.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String phoneNumber;

    private String nickname;

    @CreationTimestamp()
    private LocalDateTime registerDate; // 계정 생성일시

    private LocalDateTime lastLoginDate; // 마지막 로그인 일시

    @Enumerated(EnumType.STRING) // 문자열로 저장.
    private MemberType memberType; // 사용자 유형

}

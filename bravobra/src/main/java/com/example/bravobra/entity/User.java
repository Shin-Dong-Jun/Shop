package com.example.bravobra.entity;


import com.example.bravobra.Role;
import com.example.bravobra.UserFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 40) // not null, unique
    private String email;

    @Column(nullable = false,length = 25 )
    private String password;

    @Column(length = 1)
    private String type;

    @Column(length = 25)
    private String phoneNumber;

    @Column
    private LocalDateTime lastLoginDate;

    @Column(nullable = false, unique = true, length = 25) // not null, unique
    private String nickname;

    @Column(length = 1)
    private String isVerified; // 이메일 인증여부

    @Column
    private LocalDateTime createDate;

    @Column(length = 1) // varchar(1) -> Y or N
    private String isDormant; // 휴면계정 여부

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(userFormDto.getEmail())
                .password(passwordEncoder.encode(userFormDto.getPassword()))
                .type(userFormDto.getType())
                .phoneNumber(userFormDto.getPhoneNumber())
                .lastLoginDate(userFormDto.getLastLoginDate())
                .nickname(userFormDto.getNickname())
                .isVerified(userFormDto.getIsVerified())
                .createDate(userFormDto.getCreateDate())
                .isDormant(userFormDto.getIsDormant())
                .role(userFormDto.getRole())
                .build();
    }

}

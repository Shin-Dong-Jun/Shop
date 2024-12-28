package com.example.bravobra.dto;


import com.example.bravobra.domain.Member;
import com.example.bravobra.domain.MemberType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {

    @NotEmpty(message = "이메일 입력은 필수 입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, max = 25, message = "비밀번호는 8 ~ 25자입니다.")
    private String password;

    private String nickname;

    @Pattern(regexp = "^(\\d{2,3})-(\\d{3,4})-(\\d{4})$", message = "000-0000-0000 양식을 맞춰주세용")
    private String phoneNumber;

    private LocalDateTime registerDate;
    private MemberType memberType;

    public MemberDto() {}

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .phoneNumber(this.phoneNumber)
                .registerDate(LocalDateTime.now())
                .memberType(MemberType.MEMBER) // 기본값 설정
                .build();
    }
}

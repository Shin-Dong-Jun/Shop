package com.example.bravobra.dto;


import com.example.bravobra.domain.MemberType;
import com.example.bravobra.validation.annotation.PasswordMatch;
import com.example.bravobra.validation.annotation.UniqueNickname;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@PasswordMatch
public class MemberDto {

    @NotEmpty(message = "이메일 입력은 필수 입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotEmpty(message = "이름 입력은 필수 입니다.")
    private String name;


    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 8, max = 25, message = "비밀번호는 8 ~ 25자입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}", message = "비밀번호는 최소 하나의 숫자, 하나의 영문자, 하나의 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;

    // 비밀번호 일치 여부 체크 (어노테이션을 활용한 방법)
    public boolean isPasswordMatch() {
        return password != null && password.equals(passwordConfirm);
    }

    @NotBlank(message = "닉네임 입력은 필수 입력입니다.")
    @UniqueNickname // 내가 만든 어노테이션.
    private String nickname;

    @Pattern(regexp = "^(\\d{2,3})-(\\d{3,4})-(\\d{4})$",message = "000-0000-0000 양식을 맞춰주세용")
    private String phoneNumber;

    private LocalDateTime registerDate;

    private MemberType memberType;
}

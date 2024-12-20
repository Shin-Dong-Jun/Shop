package com.example.bravobra.controller;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberForm {

    @NotEmpty(message = "이메일 입력은 필수 입니다.")
    private String email;

    private String password;

    private String nickname;

    private int phoneNumber;
}

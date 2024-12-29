package com.example.bravobra.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class LoginDto {


        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "유효하지 않은 이메일 형식입니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

}

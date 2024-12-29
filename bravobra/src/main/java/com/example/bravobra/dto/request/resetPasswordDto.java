package com.example.bravobra.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class resetPasswordDto {


    private String email;

    @NotBlank(message = "필수 입력 사항입니다.")
    @Size(min = 8, max = 25, message = "비밀번호는 8 ~ 25자입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}", message = "비밀번호는 최소 하나의 숫자, 하나의 영문자, 하나의 특수문자를 포함해야 합니다.")
    private String newPassword;

    @NotBlank(message = "필수 입력 사항입니다.")
    @Size(min = 8, max = 25, message = "비밀번호는 8 ~ 25자입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}", message = "비밀번호는 최소 하나의 숫자, 하나의 영문자, 하나의 특수문자를 포함해야 합니다.")
    private String newPasswordConfirm;
}

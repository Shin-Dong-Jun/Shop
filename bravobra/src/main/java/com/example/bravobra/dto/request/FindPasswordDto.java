package com.example.bravobra.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FindPasswordDto {

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "^(\\d{2,3})-(\\d{3,4})-(\\d{4})$",message = "000-0000-0000 양식을 맞춰주세요.")
    private String phoneNumber;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

}

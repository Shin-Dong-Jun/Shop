package com.example.bravobra.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.boot.model.source.internal.hbm.XmlElementMetadata;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDtoRequest {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 25, message = "닉네임은 8자이상, 16자 이하로 입력하세요")
    private String pw;

    @Pattern(regexp = "^((0\\d{1,2})-(\\d{3,4})-(\\d{4}))$", message = "전화번호 정규표현식")
    private String hp;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Length(min = 3, max = 16, message = "닉네임은 3자이상, 16자 이하로 입력하세요")
    private String nickname;

    private String type;
}

package com.example.bravobra.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class FindIdDto {


    @Pattern(regexp = "^(\\d{2,3})-(\\d{3,4})-(\\d{4})$",message = "000-0000-0000 양식을 맞춰주세요.")
    private String phoneNumber;

    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

}

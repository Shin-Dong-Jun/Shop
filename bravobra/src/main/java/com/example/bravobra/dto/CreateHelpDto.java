package com.example.bravobra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateHelpDto {
   @NotBlank(message = "회원번호를 입력하세요")
   private Long userId;

   @NotBlank(message = "제목을 입력하세요")
   @Size(max = 100, message = "100자 이하로 입력하세요")
   private String title;

   @Size(max = 500, message = "500자 이하로 입력하세요")
   private String content;

   @NotBlank(message = "닉네임을 입력하세요")
   @Size(max = 25, message = "닉네임은 25자 이하로 입력해주세요")
   private String nickname;
}

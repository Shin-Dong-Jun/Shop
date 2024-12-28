package com.example.bravobra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class UpdateHelpDto {
   @NotNull(message = "존재하지 않는 게시판입니다")
   private Long helpId;

   @NotNull(message = "회원번호를 입력하세요")
   private Long memberId;

   @NotBlank(message = "제목을 입력하세요")
   @Size(max = 100, message = "100자 이하로 입력하세요")
   private String title;

   @Size(max = 500, message = "500자 이하로 입력하세요")
   private String content;
}

package com.example.bravobra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateCommentDto {
   @NotBlank
   private Long helpId;

   @NotBlank(message = "닉네임을 입력하세요")
   @Size(max = 25, message = "닉네임은 25자 이하로 입력하세요")
   private String cNickname;

   @NotBlank(message = "내용을 입력하세요")
   @Size(max = 500, message = "내용은 최대 500자 이하로 입력하세요")
   private String cContent;
}

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
public class CreateCommentDto {
   @NotNull(message = "글 번호를 입력하세요")
   private Long helpId;

   @NotBlank(message = "닉네임을 입력하세요")
   @Size(max = 25, message = "닉네임은 25자 이하로 입력하세요")
   private String cNickname;

   @NotBlank(message = "내용을 입력하세요")
   @Size(max = 300, message = "내용은 최대 300자 이하로 입력하세요")
   private String cContent;
}

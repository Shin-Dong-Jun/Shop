package com.example.bravobra.dto;

import com.example.bravobra.domain.Comment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateCommentDtoTest {
   @Autowired
   private Validator validator;

   @Test
   @DisplayName("dto에서 Comment Entity로 변환한다")
   void testToCommentEntity() {
      //given
      CreateCommentDto validCommentDto = CreateCommentDto.builder()
              .helpId(1L)
              .cNickname("닉네임1")
              .cContent("댓글이에요")
              .build();

      //when
      Comment comment = Comment.toCommentEntity(validCommentDto);

      //then
      assertNotNull(comment);
      System.out.println(comment);

      assertEquals(validCommentDto.getHelpId(), comment.getHelpId());
      assertEquals(validCommentDto.getCNickname(), comment.getCNickname());
      assertEquals(validCommentDto.getCContent(), comment.getCContent());
   }

   @Test
   @DisplayName("DTO값이 없거나 null인 경우 유효성 검증 오류가 발생해야 한다")
   void createCommentDtoNullValidation() {
      //given
      CreateCommentDto invalidDto = CreateCommentDto.builder()
              .helpId(null)
              .cNickname("닉네임222")
              .cContent(null)
              .build();

      //when
      Set<ConstraintViolation<CreateCommentDto>> validateInfo = validator.validate(invalidDto);

      //then
      assertFalse(validateInfo.isEmpty());
      assertEquals(2, validateInfo.size());
   }

   @Test
   @DisplayName("설정한 길이값을 벗어나면 유효성 검증 오류가 발생해야 한다")
   void createCommentDtoSizeValidation() {
      //given
      String cNickName = "닉".repeat(26);
      String cContent = "1".repeat(301);

      CreateCommentDto invalidDto = CreateCommentDto.builder()
              .helpId(1L)
              .cNickname(cNickName)
              .cContent(cContent)
              .build();

      //when
      Set<ConstraintViolation<CreateCommentDto>> validateInfo = validator.validate(invalidDto);

      //then
      assertFalse(validateInfo.isEmpty());
      assertEquals(2, validateInfo.size());
   }
}
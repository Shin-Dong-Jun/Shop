package com.example.bravobra.dto;

import com.example.bravobra.domain.Help;
import com.example.bravobra.domain.Member;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class CreateHelpDtoTest {

   private Validator validator;

   private static final Logger logger = LoggerFactory.getLogger(CreateHelpDtoTest.class);

   @Mock
   private Member mockMember;

   @InjectMocks
   private Help help;

   //테스트 코드에서는 Validation이 동작X, Validator를 수동 생성 필요
   @BeforeEach
   void setUp() {
      validator = Validation.buildDefaultValidatorFactory().getValidator();

      MockitoAnnotations.openMocks(this);
      when(mockMember.getMemberId()).thenReturn(1L);
   }

   @Test
   @DisplayName("Dto에서 Entity로 변환한다")
   void testToHelpEntity() {
      // Given
      CreateHelpDto validDto = CreateHelpDto.builder()
              .memberId(1L)
              .title("title")
              .content("content")
              .nickname("닉네임")
              .build();

      // When
      Help help = Help.toHelpEntity(validDto, mockMember);

      // Then
      assertNotNull(help);
      assertEquals(validDto.getMemberId(), help.getMember().getMemberId());
      assertEquals(validDto.getTitle(), help.getTitle());
      assertEquals(validDto.getContent(), help.getContent());
      assertEquals(validDto.getNickname(), help.getNickname());

      logger.info("-----> Help: {}", help);
      logger.info("-----> Member ID: {}", help.getMember().getMemberId());
   }

   @Test
   @DisplayName("DTO값이 없거나 null인 경우 유효성 검증 오류가 발생해야 한다")
   void createHelpDtoNullValidation() {
      // Given: DTO 객체 생성
      CreateHelpDto invalidDto = CreateHelpDto.builder()
              .memberId(null)
              .title("")
              .nickname("")
              .build();

      // When
      Set<ConstraintViolation<CreateHelpDto>> validateInfo = validator.validate(invalidDto);
      System.out.println("validateInfo: " + validateInfo);

      // Then
      assertFalse(validateInfo.isEmpty()); //isEmpty -> false
      //userId, title, nickName 총 3개
      assertEquals(3, validateInfo.size());
   }

   @Test
   @DisplayName("설정한 길이값을 벗어나면 유효성 검증 오류가 발생해야 한다")
   void createHelpDtoSizeValidation() {
      // Given
      String overTitle = "a".repeat(101);
      String overContent = "a".repeat(501);

      CreateHelpDto invalidDto = CreateHelpDto.builder()
              .memberId(1L)
              .title(overTitle)
              .nickname(overContent)
              .build();

      // When
      Set<ConstraintViolation<CreateHelpDto>> validateInfo = validator.validate(invalidDto);
      System.out.println("validateInfo: " + validateInfo);

      // Then
      assertFalse(validateInfo.isEmpty());
      //title, nickName 총 2개
      assertEquals(2, validateInfo.size());
   }
}
package com.example.bravobra.domain;

import com.example.bravobra.dto.CreateHelpDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Help {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long helpId;

   @JoinColumn(nullable = false)
   private Long userId;  //회원번호(아이디x)

   @Column(length = 100)
   private String title;

   @Column(length = 500)
   private String content;

   @Column(length = 25)
   private String nickname;

   @CreationTimestamp
   private LocalDateTime wDate;

   //@ColumnDefault("0") Integer로 생성된다
   @Column(columnDefinition = "int default 0")
   private int viewCnt;

   public static Help toHelpEntity(CreateHelpDto dto) {
      return Help.builder()
              .userId(dto.getUserId())
              .title(dto.getTitle())
              .content(dto.getContent())
              .nickname(dto.getNickname())
              .build();
   }
}

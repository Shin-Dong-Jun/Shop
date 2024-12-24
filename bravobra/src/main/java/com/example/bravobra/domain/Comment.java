package com.example.bravobra.domain;

import com.example.bravobra.dto.CreateCommentDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long commentId;

   @ManyToOne
   @JoinColumn(name="help_id", nullable = false)
   private Help help;

   @Column(length = 25)
   private String cNickname;

   @CreationTimestamp
   @Column(name = "c_w_date")
   private LocalDateTime wDate;

   @Column(length = 300)
   private String cContent;

   public static Comment toCommentEntity(CreateCommentDto dto, Help help){
      return Comment.builder()
              .help(help)
              .cNickname(dto.getCNickname())
              .cContent(dto.getCContent())
              .build();
   }
}

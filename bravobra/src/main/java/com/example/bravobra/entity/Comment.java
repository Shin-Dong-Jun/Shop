package com.example.bravobra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @NotNull
   private Long commentId;

   @JoinColumn(name = "help_id", nullable = false)
   @NotNull
   private Long helpId;

   @Column(length = 25)
   private String writer;

   private LocalDateTime wDate;

   @Column(length = 300)
   private String content;
}

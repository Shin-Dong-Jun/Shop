package com.example.bravobra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Help {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @NotNull
   private Long helpId;

   @JoinColumn(name = "user_id", nullable = false)
   @NotNull
   private Long user_id;

   @Column(length = 100)
   private String title;

   @Column(length = 500)
   private String content;

   @Column(length = 25)
   private String nickname;

   private LocalDateTime wDate;

   private int viewCnt;
}

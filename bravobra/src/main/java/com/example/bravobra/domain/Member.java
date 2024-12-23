package com.example.bravobra.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long memberId;

}

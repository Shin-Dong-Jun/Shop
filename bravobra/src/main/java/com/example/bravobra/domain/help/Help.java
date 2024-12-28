package com.example.bravobra.domain.help;

import com.example.bravobra.domain.Member;
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

   @ManyToOne
   @JoinColumn(name="member_id", nullable = false)
   private Member member;  //회원번호(아이디x)

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

   public static Help toHelpEntity(CreateHelpDto dto, Member member) {
      return Help.builder()
              .member(member)
              .title(dto.getTitle())
              .content(dto.getContent())
              .nickname(dto.getNickname())
              .build();
   }

   public void increaseViewCnt() {
      this.viewCnt++;
   }

   public void update(String title, String content) {
      this.title = title;
      this.content = content;
   }
}

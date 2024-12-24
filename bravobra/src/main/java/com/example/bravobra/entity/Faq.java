package com.example.bravobra.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Faq {

    @Id
    @Column(name ="faq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String nickname;

    @Column(name ="w_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime wDate;

    @Column(name ="view_cnt",columnDefinition = "integer default 0")
    private Long viewCnt;

}

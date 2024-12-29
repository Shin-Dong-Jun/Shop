package com.example.bravobra.entity;


import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(name = "faq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqId;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;


    @Column(name = "w_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime wDate;


    @Column(name = "view_cnt", columnDefinition = "integer default 0")
    private long viewCnt;

    @Column(nullable = false)
    private String writer = "관리자";

    public void incrementViewCnt() {
        this.viewCnt++;
    }



    @Builder
    public Faq(String title, String content) {
        this.title = title;
        this.content = content;
        this.writer = "관리자"; // 기본값 설정
        this.wDate = LocalDateTime.now(); // 작성 시간 설정
    }

}

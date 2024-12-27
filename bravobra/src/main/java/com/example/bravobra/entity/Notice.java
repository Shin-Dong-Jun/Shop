package com.example.bravobra.entity;

import com.example.bravobra.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;


@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Notice {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

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

    @Column(name = "notice_type",nullable = false)
    private String noticeType = "일반";

    private Boolean fix;


    @Column(nullable = false)
    private String writer = "관리자";

    public void incrementViewCnt() {
        this.viewCnt++;
    }

}


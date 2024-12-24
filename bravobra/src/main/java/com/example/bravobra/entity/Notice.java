package com.example.bravobra.entity;

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

    @Column(name = "member_id", nullable = false)
    private long memberId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "w_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime wDate;

    @Column(name = "view_cnt", columnDefinition = "integer default 0")
    private long viewCnt;

//    @ColumnDefault("N")
//    @Column(name = "is_pin", columnDefinition = "varchar(1)")
//    private String isPin;


    @Column(nullable = false)
    private String writer = "관리자";

    void incrementViewCnt() {
        this.viewCnt++;
    }

//    void urgent(){
//    if(this.getIsPin().equals('Y')){
//
//    }
//
//    }



}


package com.example.bravobra.dto.response;

import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class ResponseFaqDto {

    private Long faqId;
    private Long memberId;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime wDate;
    private int viewCnt;

}
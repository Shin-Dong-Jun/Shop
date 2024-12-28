package com.example.bravobra.service;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.FaqRepository;
import com.example.bravobra.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
//dto를 entity로 변환해주고, 저장하는게 service의 일이다.
public class FaqService {

    private final FaqRepository faqRepository;
    private final MemberRepository memberRepository;

    //1.쓰기 등록
    public Faq postFaq(RequestFaqDto requestFaqDto, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow();

        Faq faq = Faq.builder()
                .member(member)
                .title(requestFaqDto.getTitle())
                .content(requestFaqDto.getContent())
                .viewCnt(0)
                .writer("관리자")
                .wDate(LocalDateTime.now())
                .build();

           return faqRepository.save(faq);
    }


    //2.전체조회
    public List<Faq> getAllFaq() {
        return faqRepository.findAll(Sort.by(Sort.Direction.DESC, "faqId"));
    }


    //3.단일조회

    public Faq getFaqById(long faqId) {
        log.info("Fetching FAQ with id: " + faqId);
        Faq faq = faqRepository.findById(faqId)
                .orElseThrow(() -> new IllegalArgumentException("없는 게시글 입니다."));
        faq.incrementViewCnt();
        log.info("Incrementing view count for FAQ id: " + faqId);
        faqRepository.save(faq);
        return faq;
    }


    //4.검색 조회
    public List<Faq> findByTitle(String title) {
        List<Faq> faq = faqRepository.findByTitleContaining(title);
        if (faq.isEmpty()) {
            throw new IllegalArgumentException("없는 키워드 입니다.");
        }
        return faq;
    }


    //5.삭제
    public void removeFaq(Long faqId) {
        faqRepository.deleteById(faqId);
    }


    //6.수정
    public Faq modifyFaq(RequestFaqDto requestFaqDto, long faqId) {

        Faq faq = faqRepository.findById(faqId)
                .orElseThrow(() -> new IllegalArgumentException("없는 게시글 입니다."));

        faq = Faq.builder()
                .faqId(faq.getFaqId())
                .member(faq.getMember())
                .title(requestFaqDto.getTitle())
                .content(requestFaqDto.getContent())
                .wDate(LocalDateTime.now())
                .viewCnt(faq.getViewCnt())
                .writer("관리자")
                .build();

        faqRepository.save(faq);
        return faq;
    }


    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow();

    }







}
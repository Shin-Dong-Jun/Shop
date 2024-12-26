package com.example.bravobra.service;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.RequestNoticeDto;
import com.example.bravobra.entity.Notice;
import com.example.bravobra.repository.MemberRepository;
import com.example.bravobra.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {


    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    //1.쓰기 등록
    public Notice postNotice(RequestNoticeDto requestNoticeDto, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow();


        Notice notice = Notice.builder()
                        .member(member)
                        .title(requestNoticeDto.getTitle())
                        .content(requestNoticeDto.getContent())
                        .wDate(LocalDateTime.now())
                        .viewCnt(0l)
                        .writer("관리자")
                        .build();

        Notice save = noticeRepository.save(notice);
        return notice;
    }


    //2.전체조회
    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();}


    //3.단일조회
    public Notice getNoticeById(long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("없는 게시글 입니다."));
        return notice;
    }


    //4.검색 조회
    public List<Notice> findByTitle(String title) {
        List<Notice> notices = noticeRepository.findByTitleContaining(title);
        if (notices.isEmpty()) {
            throw new IllegalArgumentException("없는 키워드 입니다.");
        }
        return notices;
    }


    //5.삭제
    public void removeNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }


    //6.수정
    public Notice modifyFaq(RequestNoticeDto requestNoticeDto, long noticeId) {

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("없는 게시글 입니다."));

        notice = Notice.builder()
                .noticeId(notice.getNoticeId())
                .member(notice.getMember())
                .title(requestNoticeDto.getTitle())
                .content(requestNoticeDto.getContent())
                .wDate(LocalDateTime.now())
                .viewCnt(notice.getViewCnt())
                .writer("관리자")
                .build();

        noticeRepository.save(notice);
        return notice;
    }

}



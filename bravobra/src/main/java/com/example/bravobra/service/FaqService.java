package com.example.bravobra.service;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.repository.FaqRepository;
import com.querydsl.core.types.ExpressionException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
//dto를 entity로 변환해주고, 저장하는게 service의 일이다.
public class FaqService {

    private final FaqRepository faqRepository;


    //1.쓰기 등록
    public Faq postFaq(RequestFaqDto requestFaqDto, Long memberId, String nickname) {

        Faq faq = Faq.builder()
                .memberId(memberId)
                .title(requestFaqDto.getTitle())
                .content(requestFaqDto.getContent())
                .nickname(nickname)
                .wDate(LocalDateTime.now())
                .viewCnt(0l)
                .build();

        faqRepository.save(faq);
        return faq;
    }



    //2.전체조회
    public List<Faq> getAllFaq() {
        return faqRepository.findAll();
    }


    //3.부분조회(일단...... ㅜㅜ 근데 제목으로 검색하고싶은디.. 머.. 배송, 비밀번호 이런식으로 검색가능하게)
    public Faq getFaq(Long memberId) {
        Faq faq = faqRepository.findById(memberId)
                .orElseThrow(() -> new ExpressionException("없는 아이디 입니다."));
        return faq;
    }


    //4.삭제
    public void removeFaq(Long faqId) {
        faqRepository.deleteById(faqId);
    }



//    //5.수정
//    public Faq modifyFaq(RequestFaqDto requestFaqDto, Long faqid) {
//
//
//        Faq faq = Faq.builder()
//                .memberId(requestFaqDto.getMemberId())
//                .title(requestFaqDto.getTitle())
//                .content(requestFaqDto.getContent())
//                .nickname(requestFaqDto.getNickname())
//                .wDate(LocalDateTime.now())
//                .viewCnt(requestFaqDto.getViewCnt())
//                .build();
//
//            faqRepository.save(faq);
//        return faq;
//    }
}

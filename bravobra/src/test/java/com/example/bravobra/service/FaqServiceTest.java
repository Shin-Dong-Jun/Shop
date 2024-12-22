package com.example.bravobra.service;

import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.repository.FaqRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;




//서비스테스트.. 무얼 테스트하는가? 디티오를 엔티티로 잘 변환해줬는지..저장잘됐는지..
@ExtendWith(MockitoExtension.class)
    class FaqServiceTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    FaqService faqService;


    @DisplayName("1.등록 서비스 첫 테스트.....히히")
    @Test
    void postFaq() {
        //  given
        RequestFaqDto requestFaqDto = RequestFaqDto.builder()
                .memberId(1234L)
                .title("제목입니다")
                .content("내용입니다")
                .nickname("림이")
                .build();

        RequestFaqDto requestFaqDto2 = RequestFaqDto.builder()
                .memberId(5678l)
                .title("제목2입니다")
                .content("내용2입니다")
                .nickname("리미")
                .build();
        // when
        Faq result = faqService.postFaq(requestFaqDto);
        Faq result2 = faqService.postFaq(requestFaqDto2);

        // then
        assertTrue(true);
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals("림이", result.getNickname());
        assertEquals("리미", result2.getNickname());

    }


    @DisplayName("2.전체 조회 테스트")
    @Test
    void getAllFaq() throws Exception {
        // given
        Faq Faq1 = Faq.builder()
                .memberId(1234l)
                .title("제목입니다")
                .content("내용입니다")
                .nickname("림이")
                .build();

        Faq Faq2 = Faq.builder()
                .memberId(5678l)
                .title("제목2입니다")
                .content("내용2입니다")
                .nickname("리미")
                .build();

        List<Faq> allFaq = Arrays.asList(Faq1, Faq2);

        // when
        when(faqRepository.findAll()).thenReturn(allFaq);
        List<Faq> result = faqService.getAllFaq();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @DisplayName("부분 조회 테스트")
    @Test
    void getSearchFaq() throws Exception {
        // given
        Faq Faq1 = Faq.builder()
                .memberId(1234l)
                .title("제목입니다")
                .content("내용입니다")
                .nickname("림이")
                .build();

        Faq Faq2 = Faq.builder()
                .memberId(8888l)
                .title("제목2입니다")
                .content("내용2입니다")
                .nickname("림이2")
                .build();

        Faq Faq3 = Faq.builder()
                .memberId(5678l)
                .title("제목3입니다")
                .content("내용3입니다")
                .nickname("코기")
                .build();

        // when
        when(faqRepository.findById(5678l)).thenReturn(Optional.of(Faq3));
        Faq result = faqService.getFaq(5678l);

        // then
        assertNotNull(result);
        assertEquals("코기", result.getNickname());
    }

    @DisplayName("삭제 테스트")
    @Test
    void getRemoveFaq() throws Exception {
        // given
        Faq Faq1 = Faq.builder()
                .memberId(1234l)
                .title("제목입니다")
                .content("내용입니다")
                .nickname("림이")
                .build();

        Faq Faq2 = Faq.builder()
                .memberId(8888l)
                .title("제목2입니다")
                .content("내용2입니다")
                .nickname("림이2")
                .build();

        Faq Faq3 = Faq.builder()
                .memberId(5678l)
                .title("제목3입니다")
                .content("내용3입니다")
                .nickname("코기")
                .build();

        faqService.removeFaq(Faq3.getMemberId());

    }





    //수정테스트........ ㅜㅜ
    @DisplayName("수정 테스트")
    @Test
    void getModifyFaq() throws Exception {
        // given
        RequestFaqDto requestFaqDto = RequestFaqDto.builder()
                .memberId(1234L)
                .title("이전제목")
                .content("내용입니다")
                .nickname("림이")
                .build();

        // when
        Faq result = faqService.postFaq(requestFaqDto);

        // then
        assertTrue(true);
        assertNotNull(result);
        assertEquals("이전제목", result.getTitle());
    }
}




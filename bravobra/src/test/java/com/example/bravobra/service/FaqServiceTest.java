package com.example.bravobra.service;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.repository.FaqRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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

        Long MemberId = 1234L;
        String Nickname = "림이";

        Long MemberId2 = 5678L;
        String Nickname2 = "코기";


        RequestFaqDto requestFaqDto = RequestFaqDto.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();

        RequestFaqDto requestFaqDto2 = RequestFaqDto.builder()
                .title("제목2입니다")
                .content("내용2입니다")
                .build();


        // when
        Faq result = faqService.postFaq(requestFaqDto, MemberId);
        Faq result2 = faqService.postFaq(requestFaqDto2, MemberId2);


        // then


        assertNotNull(result);
        assertNotNull(result2);
        assertEquals("제목2입니다", result2.getTitle());
        assertEquals("내용입니다", result.getContent());


    }


    @DisplayName("2.전체 조회 테스트")
    @Test
    void getAllFaq() throws Exception {
        // given
        Faq Faq1 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목입니다")
                .content("내용입니다")
                .build();

        Faq Faq2 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목2입니다")
                .content("내용2입니다")
                .build();

        List<Faq> allFaq = Arrays.asList(Faq1, Faq2);

        // when
        when(faqRepository.findAll()).thenReturn(allFaq);
        List<Faq> result = faqService.getAllFaq();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @DisplayName("검색 조회 테스트")
    @Test
    void getSearchFaq() throws Exception {
        // given
        Faq Faq1 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목입니다")
                .content("내용입니다")
                .build();

        Faq Faq2 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목3입니다")
                .content("내용2입니다")
                .build();

        Faq Faq3 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목3입니다")
                .content("내용3입니다")
                .build();

        List<Faq> faqList = new ArrayList<>();
        faqList.add(Faq1);
        faqList.add(Faq2);
        faqList.add(Faq3);

        List<Faq> titleList = new ArrayList<>();
        for (Faq faq : faqList) {
            if (faq.getTitle().contains("목3")){
                titleList.add(faq);
            }
        }

        // when
        when(faqRepository.findByTitleContaining("목3")).thenReturn(titleList);
        List<Faq> result = faqService.findByTitle("목3");

        // then
        assertNotNull(result);
        assertEquals(2, result.size());

    }





    @DisplayName("삭제 테스트")
    @Test
    void getRemoveFaq() throws Exception {

        // given
        Faq Faq1 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목입니다")
                .content("내용입니다")
                .build();

        Faq Faq2 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목2입니다")
                .content("내용2입니다")
                .build();

        Faq Faq3 = Faq.builder()
//                .member(Member.builder().build())
                .title("제목3입니다")
                .content("내용3입니다")
                .build();

//        faqService.removeFaq(Faq3.getMember().getId());

    }





//    //수정테스트........ ㅜㅜ
//    @DisplayName("수정 테스트")
//    @Test
//    void getModifyFaq() throws Exception {
//        // given
//        RequestFaqDto requestFaqDto = RequestFaqDto.builder()
//                .memberId(1234L)
//                .title("이전제목")
//                .content("내용입니다")
//                .nickname("림이")
//                .build();
//
//        // when
//        Faq result = faqService.postFaq(requestFaqDto);
//
//        // then
//        assertTrue(true);
//        assertNotNull(result);
//        assertEquals("제목입니다", result.getTitle());
//    }
}




package com.example.bravobra.controller;

import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.entity.Faq;
import com.example.bravobra.repository.FaqRepository;
import com.example.bravobra.service.FaqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.svm.core.annotate.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(FaqController.class)


@SpringBootTest
@AutoConfigureMockMvc
class FaqControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @Mock
    private FaqService faqService;

   @InjectMocks
   private FaqController faqController;


    @DisplayName("등록 컨트롤러 첫 테스트.....히히")
    @Test
    public void postFaq() throws Exception {
        //given

        Long MemberId = 1234L;
        String Nickname = "림이";

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("memberId", MemberId);

        RequestFaqDto requestFaqDto = RequestFaqDto.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();

        Faq result = faqService.postFaq(requestFaqDto, 1234L);
        System.out.println("Faq 객체: " + result);
        //가짜 시뮬레이션 돌리기.
            mockMvc.perform(MockMvcRequestBuilders.post("/faq/post")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("title",result.getTitle())
                            .param("content",result.getContent())
                            .session(session))
                    .andExpect(status().is3xxRedirection());
        }
    }
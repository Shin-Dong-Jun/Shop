package com.example.bravobra.controller;

import com.example.bravobra.dto.request.RequestFaqDto;
import com.example.bravobra.service.FaqService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(FaqController.class)


@SpringBootTest
@AutoConfigureMockMvc
class FaqControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("등록 컨트롤러 첫 테스트.....히히")
    @Test
    public void postFaq() throws Exception {
        //given

        Long MemberId = 1234L;
        String Nickname = "림이";

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("memberId", MemberId);
        session.setAttribute("nickname", Nickname);

        RequestFaqDto requestFaqDto = RequestFaqDto.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();


        //가짜 시뮬레이션 돌리기.
            mockMvc.perform(MockMvcRequestBuilders.post("/faq")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("title",requestFaqDto.getTitle())
                            .param("content",requestFaqDto.getContent())
                    )
                    //then
                    .andExpect(status().isCreated());

    }
}
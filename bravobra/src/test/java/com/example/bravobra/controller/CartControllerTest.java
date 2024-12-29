package com.example.bravobra.controller;

import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = CartController.class)
@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @MockitoBean
    CartService cartService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("업데이트하는 장바구니")
    void 파라미터잘들어가나() throws Exception {
        // given



//                .andExpect(model().attribute("itemCartDtoRequest",
//                        Matchers.hasProperty(
//                                "size", Matchers.is("65A"))));
//                ).andExpect(model().attribute("inOutSlackResponseDto",
//                        Matchers.hasProperty(
//                                "scheduledIn", Matchers.is(slackResponse1.getScheduledIn())))
//                );
        //when

        //then

    }
}
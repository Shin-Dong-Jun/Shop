package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.LoginDto;
import com.example.bravobra.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private LoginController loginController;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUP(){
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void login() throws Exception{
        //given
        LoginDto loginDto = LoginDto.builder() // 클래스 이름을 통해 builder 호출
                .email("tlsehdwns147@naver.com")
                .password("12341234")
                .build();

        Member member = Member.builder()
                .email("tlsehdwns147@naver.com")
                .build();

        //when
        when(loginService.login("tlsehdwns147@naver.com", "12341234")).thenReturn(member);

        String view = loginController.login(loginDto, bindingResult, null, request, response);

        //then

//        assertEquals("redirect:/", view);
        assertNotNull(request.getSession().getAttribute("loginMember"));

    }

}
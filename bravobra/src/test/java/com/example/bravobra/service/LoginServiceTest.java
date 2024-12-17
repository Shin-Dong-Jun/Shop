package com.example.bravobra.service;

import com.example.bravobra.domain.User;
import com.example.bravobra.dto.request.SignUpDtoRequest;
import com.example.bravobra.repository.LoginRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Member;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {


    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoginRepository loginRepository;


    @Test
    @DisplayName("회원가입")
    void 회원가입() throws Exception {
        // given
        SignUpDtoRequest memberRequest = getMemberRequest();

        //when


 //then

    }

    private User createMemberEntity(SignUpDtoRequest memberRequest) {
        return User.toEntity(memberRequest);
    }

    private SignUpDtoRequest getMemberRequest() {
        return SignUpDtoRequest.builder()
                .email("email@email.com")
                .pw("password")
                .hp("010-2720-0204")
                .type("N")
                .nickname("tester1")
                .build();
    }

}
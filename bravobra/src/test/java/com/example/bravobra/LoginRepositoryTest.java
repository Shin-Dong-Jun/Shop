package com.example.bravobra;

import com.example.bravobra.domain.User;
import com.example.bravobra.dto.request.SignUpDtoRequest;
import com.example.bravobra.repository.LoginRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.image.LookupOp;

@DataJpaTest
public class LoginRepositoryTest {

    @Autowired
    private LoginRepository loginRepository;


    @Test
    @DisplayName("잘 나오나 보기")
    void login() throws Exception {
        
        // given
        SignUpDtoRequest memberRequest = getMemberRequest();

        //when
        User entity = User.toEntity(memberRequest);
        User save = loginRepository.save(entity);

        System.out.println(save);
        //then

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

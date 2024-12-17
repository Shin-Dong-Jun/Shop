package com.example.bravobra.service;

import com.example.bravobra.domain.User;
import com.example.bravobra.dto.LoginMemberResponse;
import com.example.bravobra.dto.request.LoginDtoRequest;
import com.example.bravobra.dto.request.SignUpDtoRequest;
import com.example.bravobra.repository.LoginRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginMemberResponse loginMember(LoginDtoRequest request) {


        return null;
    }

    public void singup(SignUpDtoRequest request) {
        User entity = User.toSignEntity(request);

        loginRepository.save(entity);

    }
}

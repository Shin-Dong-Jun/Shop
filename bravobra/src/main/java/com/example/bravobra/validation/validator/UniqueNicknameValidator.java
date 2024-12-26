package com.example.bravobra.validation.validator;


import com.example.bravobra.repository.MemberRepository;
import com.example.bravobra.validation.annotation.UniqueNickname;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueNicknameValidator implements ConstraintValidator<UniqueNickname, String> {

    private final MemberRepository memberRepository;


    public boolean isValid(String nickname, ConstraintValidatorContext context) {
        if (nickname == null || nickname.isEmpty()) {
            return true;

        }

        return !memberRepository.existsByNickName(nickname);
    }

}

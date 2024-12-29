package com.example.bravobra.validation.validator;


import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.validation.annotation.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, MemberDto> {

    public boolean isValid(MemberDto memberDto, ConstraintValidatorContext context) {
        if (memberDto.getPassword() == null || memberDto.getPasswordConfirm() == null) {
            return false;
        }

        if(!memberDto.getPassword().equals(memberDto.getPasswordConfirm())) {
            context.disableDefaultConstraintViolation();

            //필드에 에러가 나타나게 하기위해서
            context.buildConstraintViolationWithTemplate("비밀번호가 일치하지 않습니다.")
                    .addPropertyNode("password")
                    .addConstraintViolation();

            context.buildConstraintViolationWithTemplate("비밀번호가 일치하지 않습니다.")
                    .addPropertyNode("passwordConfirm")
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}

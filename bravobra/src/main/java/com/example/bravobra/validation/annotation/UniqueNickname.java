package com.example.bravobra.validation.annotation;

import com.example.bravobra.validation.validator.UniqueNicknameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNicknameValidator.class) // 로직 클래스 연결
@Target({ElementType.FIELD}) // 필드에만 적용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNickname {

    String message() default "이미 사용 중인 닉네임입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

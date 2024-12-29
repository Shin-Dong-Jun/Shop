package com.example.bravobra.service;


import com.example.bravobra.domain.LoginAttempt;
import com.example.bravobra.repository.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {

    public static final int MAX_ATTEMPTS = 3;
    public static final long LOCK_TIME_MINUTES = 10;

    private final LoginAttemptRepository loginAttemptRepository;

    @Transactional
    public void recordFailAttempt(String email) {
        LoginAttempt attempt = loginAttemptRepository.findByEmail(email)
                .orElse(LoginAttempt.builder()
                        .email(email)
                        .attempts(0)
                        .lastAttempt(LocalDateTime.now())
                        .build());

        //실패 시 횟수 증가
        attempt.increaseAttempts();

        // 실패 횟수 초과 시 계정 잠금
        if(attempt.getAttempts() >= MAX_ATTEMPTS) {
            attempt.lockAccount(LOCK_TIME_MINUTES);
        }
        loginAttemptRepository.save(attempt);
    }
    // 로그인 성공 시 시도 횟수 초기화.
    @Transactional
    public void resetAttempts(String email) {
        loginAttemptRepository.findByEmail(email).ifPresent(loginAttemptRepository::delete);
    }
    //현재 시도 횟수
    @Transactional(readOnly = true)
    public int getAttempts(String email) {
        return loginAttemptRepository.findByEmail(email)
                .map(LoginAttempt::getAttempts)
                .orElse(0);
    }

    @Transactional(readOnly = true)
    public boolean isAccountLocked(String email){
        return loginAttemptRepository.findByEmail(email)
                .map(attempt -> attempt.getLockedUntil() != null && attempt.getLockedUntil().isAfter(LocalDateTime.now()))
                .orElse(false);
    }
}

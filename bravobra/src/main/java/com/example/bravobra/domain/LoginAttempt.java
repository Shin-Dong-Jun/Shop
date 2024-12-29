package com.example.bravobra.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoginAttempt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Builder.Default
    private int attempts = 0;

    private LocalDateTime lockedUntil;

    private LocalDateTime lastAttempt;

    // 실패 시 시도 횟수 증가
    public void increaseAttempts() {
        this.attempts += 1;
        this.lastAttempt = LocalDateTime.now();
    }

    // 잠금 상태 설정
    public void lockAccount(long lockTimeMinutes) {
        this.lockedUntil = LocalDateTime.now().plusMinutes(lockTimeMinutes);
    }

    public boolean isLocked(){
        return this.lockedUntil != null && this.lockedUntil.isAfter(LocalDateTime.now());
    }




}

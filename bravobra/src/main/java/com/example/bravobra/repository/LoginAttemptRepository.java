package com.example.bravobra.repository;

import com.example.bravobra.domain.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Integer> {

    Optional<LoginAttempt> findByEmail(String email);
    
    void deleteByEmail(String email);
}

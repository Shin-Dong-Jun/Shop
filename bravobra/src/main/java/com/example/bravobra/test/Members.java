package com.example.bravobra.test;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "members")
//    private List<Session> sessions = new ArrayList<>();
//
//    public Session addSession() {
//        Session session = Session.builder()
//                .members(this)
//                .build();
//        sessions.add(session);
//
//        return session;
//    }

    @Builder
    public Members(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}

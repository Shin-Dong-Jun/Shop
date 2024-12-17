package com.example.bravobra.domain;

import com.example.bravobra.dto.request.SignUpDtoRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
@Table(name = "members")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String pw;

    @ColumnDefault("'N'")
    private String type;

    @Column(name = "last_login_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLoginDate;

    private String nickname;

    private String isVerified;

    @CreationTimestamp
    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime creaeteDate;

    @ColumnDefault("'N'")
    @Column(name = "is_dornmant")
    private String dormant;


    public static User toSignEntity(SignUpDtoRequest memberRequest) {
        return User.builder()
                .nickname(memberRequest.getNickname())
                .pw(memberRequest.getPw())
                .email(memberRequest.getEmail())
                .type(memberRequest.getType())
                .build();
    }

    public static User TesttoEntity(SignUpDtoRequest memberRequest) {
        return User.builder()
                .nickname(memberRequest.getNickname())
                .pw(memberRequest.getPw())
                .email(memberRequest.getEmail())
                .type(memberRequest.getType())
                .build();
    }
}

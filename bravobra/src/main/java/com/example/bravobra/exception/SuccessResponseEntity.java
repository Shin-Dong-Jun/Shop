package com.example.bravobra.exception;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
public class SuccessResponseEntity<E> {
    private String message;
    private boolean success;
    private E data;

    public static ResponseEntity<SuccessResponseEntity> toResponseEntity(String massage, Object data) {
        return ResponseEntity
                .status(200)
                .body(SuccessResponseEntity.builder()
                        .message(massage)
                        .success(true)
                        .data(data)
                        .build()
                );
    }
}
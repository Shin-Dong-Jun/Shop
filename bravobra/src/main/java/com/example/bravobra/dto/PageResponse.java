package com.example.bravobra.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // 널값은 전달하지 않는다
public record PageResponse(
        String message,
        Object data,
        Integer totalPages,
        Long totalElements,
        Integer size,
        Integer number

) {
    public static PageResponse of(String message, Page page) {
        return PageResponse.builder()
                .message(message)
                .data(page.getContent())
                .number(page.getNumber())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .build();
    }
}





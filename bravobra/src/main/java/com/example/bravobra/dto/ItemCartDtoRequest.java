package com.example.bravobra.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ItemCartDtoRequest(
        @NotBlank
        String size,
        @NotBlank
        String color,
        @Min(1)
        int qnt,
        @NotBlank
        Long itemId,
        @NotBlank
        String optionValues) {
}

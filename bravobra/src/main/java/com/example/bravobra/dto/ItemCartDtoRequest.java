package com.example.bravobra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record ItemCartDtoRequest(String size, String color, int qnt, Long itemId, String optionValues) {
}

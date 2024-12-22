package com.example.bravobra.dto;

import lombok.Builder;

@Builder
public record ItemCartDtoRequest(String size, String color, int qnt, Long itemId, String optionValues, Long cartId) {
}

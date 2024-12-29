package com.example.bravobra.dto;

import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record UpdateCartDto(
        @Min(1)
        int quantity,
        Long cartId)
{

}


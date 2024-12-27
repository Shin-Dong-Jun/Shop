package com.example.bravobra.dto;

import com.example.bravobra.domain.state.OrderState;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderDtoResponse(
    String orderId,
    OrderState orderState,
    String orderNo,
    int totalPrice,
    LocalDate orderDate,
    List<OrderProductDtoResponse> productList
)
{
}

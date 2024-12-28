package com.example.bravobra.dto;

import com.example.bravobra.domain.order.Order;
import com.example.bravobra.domain.state.OrderState;
import lombok.Builder;
import lombok.Singular;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderDtoResponse(
    Long orderId,
    OrderState orderState,
    String orderNo,
    int totalPrice,
    LocalDateTime orderDate,
    List<OrderProductDtoResponse> productList)
{

    public static OrderDtoResponse of(Order order, List<OrderProductDtoResponse> orderProduct) {
        return OrderDtoResponse.builder()
                .orderId(order.getId())
                .orderNo(order.getOrderNo())
                .orderState(order.getOrderState())
                .totalPrice(order.getTotalOrderPrice())
                .orderDate(order.getOrderDatetime())
                .productList(orderProduct)
                .build();
    }
}

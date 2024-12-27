package com.example.bravobra.dto;

import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.order.OrderProduct;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.entity.Product;
import lombok.Builder;

@Builder
public record OrderProductDtoResponse(
        String productName,
        String size,
        String color,
        String optionValues,
        int quantity,
        int orderPrice,
        String img) {

    public static OrderProductDtoResponse of(OrderProduct orderProduct, Cart cart, Option option, Product product) {
        return OrderProductDtoResponse.builder()
                .img(product.getThumbnail())
                .size(option.getSize())
                .color(option.getColor())
                .optionValues(cart.getOptionValues())
                .productName(product.getProductName())
                .orderPrice(orderProduct.getTotalOrderPrice())
                .quantity(cart.getQnt())
                .build();
    }

}

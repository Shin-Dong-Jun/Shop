package com.example.bravobra.service;

import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.order.Order;
import com.example.bravobra.domain.order.OrderProduct;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.OrderState;
import com.example.bravobra.dto.OrderDtoResponse;
import com.example.bravobra.dto.OrderProductDtoResponse;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.CartRepository;
import com.example.bravobra.repository.OrderRepository;
import com.example.bravobra.utill.OrderNumberUtill;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public void addCartOrder(Long cartId, Long userId) {

        Cart cart = cartRepository.findByCartIdWithOptionAndProduct(cartId).orElseThrow(() -> new IllegalStateException("장바구니가 없습니다."));
        Product product = cart.getOptionId().getProduct();
        Option option = cart.getOptionId();
        int totalPrice = product.getSalePrice();
        int totalDiscountPrice = totalPrice * product.getDiscountRate() / 100;
        int totalOrderPrice = totalPrice - totalDiscountPrice;

        OrderProduct orderProduct = OrderProduct.builder()
                .orderState(OrderState.PAYMENT_PENDING)
                .qtn(cart.getQnt())
                .option(option)
                .totalDiscountPrice(totalDiscountPrice)
                .totalOrderPrice(totalOrderPrice)
                .build();

        Order order = Order.builder()
                .orderState(OrderState.PAYMENT_PENDING)
                .orderNo(OrderNumberUtill.generateOrderNumber())
                .userId(userId)
                .totalProductPrice(orderProduct.getTotalOrderPrice())
                .totalDiscountPrice(orderProduct.getTotalDiscountPrice())
                .totalOrderPrice(orderProduct.getTotalOrderPrice())
                .build();

        order.addOrderProduct(orderProduct);
        Order  saveOrder = orderRepository.save(order);
        cartRepository.deleteById(cartId);
        OrderProductDtoResponse orderProductDtoResponse = OrderProductDtoResponse.of(orderProduct, cart, option, product);


    }

}

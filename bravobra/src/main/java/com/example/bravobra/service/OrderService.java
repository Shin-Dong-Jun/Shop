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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public void addCartOrder(Long cartId, Long userId) {

        Cart cart = cartRepository.findByCartIdWithOptionAndProduct(cartId)
                .orElseThrow(() -> new IllegalStateException("장바구니가 없습니다."));

        Option option = Optional.ofNullable(cart.getOptionId())
                .orElseThrow(() -> new IllegalStateException("옵션이 없습니다."));

        Product product = Optional.ofNullable(option.getProduct())
                .orElseThrow(() -> new IllegalStateException("상품이 없습니다."));

        int totalPrice = product.getSalePrice()*cart.getQnt();
        int totalDiscountPrice = product.getFixedPrice() * product.getDiscountRate() / 100 * cart.getQnt();
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
        cartRepository.deleteById(cartId);
        orderRepository.save(order);

    }

    public Page<OrderDtoResponse> getOrderList(Long userId, PageRequest pageable) {

        var orders = orderRepository.findByUserIdWithOrderProduct(userId, pageable);

        return orders.map(order -> {
            List<OrderProductDtoResponse> orderProductDtoList = order.getOrderProduct().stream().map(
                    orderProduct -> {
                        Option option = Optional.ofNullable(orderProduct.getOption())
                                .orElseThrow(() -> new IllegalStateException("옵션이 없습니다."));

                        Product product = Optional.ofNullable(option.getProduct())
                                .orElseThrow(() -> new IllegalStateException("상품이 없습니다."));
                        return OrderProductDtoResponse.of(orderProduct, option, product);
                    }
            ).collect(Collectors.toList());

            return OrderDtoResponse.of(order, orderProductDtoList);
        });
    }
}

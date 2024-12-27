package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.order.Order;
import com.example.bravobra.domain.order.OrderProduct;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.OrderState;
import com.example.bravobra.entity.Product;
import com.example.bravobra.utill.OrderNumberUtill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Test
    @DisplayName("주문을 해보자")
    void order() throws Exception {
        // given
        Product product = Product.builder()
                .productName("P1")
                .productEnglishName("섹시원더브라")
                .productContent("테스트 상품 상세설명")
                .thumbnail("model1.jpg")
                .image1("img1.jpg")
                .image2("img2.jpg")
                .image3("img3.jpg")
                .image4("img4.jpg")
                .fixedPrice(50000)
                .salePrice(40000)
                .hashTag("#섹시 #브라 #할인")
                .discountRate(20)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productRepository.save(product);

        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(1L);
        Option option = Option.builder()
                .qnt(100)
                .color("black")
                .size("65A")
                .optionId(optionId)
                .product(product)
                .build();

        optionRepository.save(option);

        Cart cart = Cart.builder()
                .userId(1L)
                .qnt(10)
                .optionValues("black/65A")
                .optionId(option)
                .build();

        cart = cartRepository.save(cart);
        //when

        OrderProduct orderProduct = OrderProduct.builder()
                .orderState(OrderState.PAYMENT_PENDING)
                .qtn(cart.getQnt())
                .totalOrderPrice(product.getFixedPrice())
                .option(option)
                .totalDiscountPrice(0)
                .build();


        Order order= Order.builder()
                .orderState(OrderState.PAYMENT_PENDING)
                .orderNo(OrderNumberUtill.generateOrderNumber())
                .userId(1L)
                .totalProductPrice(orderProduct.getTotalOrderPrice())
                .totalDiscountPrice(0)
                .totalOrderPrice(orderProduct.getTotalOrderPrice())
                .build();


        order.addOrderProduct(orderProduct);

        Order orderEntity= orderRepository.save(order);


        OrderProduct byOrder = orderProductRepository.findByOrder(orderEntity);

        //then

        Cart byCartIdWithOptionAndProduct = cartRepository.findByCartIdWithOptionAndProduct(1L).get();
//
        System.out.println(byCartIdWithOptionAndProduct.getOptionId().getProduct().getFixedPrice());

        System.out.println(byCartIdWithOptionAndProduct.getOptionId().getProduct().getProductId());
        System.out.println(byCartIdWithOptionAndProduct.getOptionId().getProduct().getCreatedAt());
//        Assertions.assertEquals(byOrder.getOrder(), orderEntity);

    }
}
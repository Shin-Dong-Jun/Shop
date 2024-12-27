package com.example.bravobra.service;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.CartRepository;
import com.example.bravobra.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CartRepository cartRepository;

    @Test
    @DisplayName("장바구니 담기 테스트")
    void postcart() throws Exception {
        Long userId = 1L;
        Long productId = 1L;
        Long cartId = 1L;
        // given
        Product product = Product.builder()
                .productId(productId)
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

        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(productId);
        Option option = Option.builder()
                .qnt(100)
                .color("black")
                .size("65A")
                .optionId(optionId)
                .product(product)
                .build();


        Cart cart = Cart.builder()
                .id(cartId)
                .userId(userId)
                .qnt(10)
                .optionValues("black/65A")
                .optionId(option)
                .build();


        when(cartRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(cart));


        //when
        orderService.addCartOrder(cartId, userId);


        //then

    }

}
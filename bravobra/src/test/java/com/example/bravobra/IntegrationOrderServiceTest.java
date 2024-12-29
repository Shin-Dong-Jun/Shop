package com.example.bravobra;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.dto.OrderDtoResponse;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.CartRepository;
import com.example.bravobra.repository.OptionRepository;
import com.example.bravobra.repository.ProductRepository;
import com.example.bravobra.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;

@SpringBootTest
public class IntegrationOrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OptionRepository optionRepository;


    private Long productId = 4L;
    private Long cartId = 1L;
    private Long userId = 1L;

    @BeforeEach
    void setUp() {
        Product product = productRepository.findById(productId).get();

        productRepository.save(product);
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

        optionRepository.save(option);

        Cart cart = Cart.builder()
                .optionValues("black/65A")
                .qnt(10)
                .optionId(option)
                .build();

        cartRepository.save(cart);

    }

    @Test
    @DisplayName("주문 서비스 잘 갖고오나 확인")
    void order() throws Exception {
        // given
        //when
         orderService.addCartOrder(cartId, userId);
        //then

    }
    @Test
    @DisplayName("주문 내역 잘 갖고 오나 페이지네이션 적용해서")
    void getOrderList() throws Exception {
        // given
        orderService.addCartOrder(cartId, userId);
        PageRequest page = PageRequest.of(0, 10,Sort.by(Sort.Direction.DESC,"orderDatetime"));
        PagedModel<OrderDtoResponse> orderList = orderService.getOrderList(userId, page);
        //when
        System.out.println(orderList.getContent()+" "+orderList.getMetadata());
        //then

    }
}

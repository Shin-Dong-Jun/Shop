package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.Category;
import com.example.bravobra.dto.ItemCartDtoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.rmi.NoSuchObjectException;

@DataJpaTest
@ActiveProfiles("test")
class OptionRepositoryTest {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private CartRepository cartRepository;

    private Option option;

    @BeforeEach
    void setUp() {
        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(1L);
        option = Option.builder()
                .qnt(100)
                .color("블랙")
                .type(Category.BRA)
                .optionId(optionId)
                .size("65A")
                .build();

//        optionRepository.save(option);

        Cart cart = Cart.builder()
                .optionId(option)
                .optionValues("블랙/65A")
                .userId(1L)
                .qnt(10)
                .build();
        Cart save = cartRepository.save(cart);

    }

    @Test
    @DisplayName("idsf")
    void getoption() throws Exception {
        // given
        Option entity = optionRepository.findByOptionId(option.getColor(), option.getSize(), option.getOptionId().getProductId()).orElseThrow(() ->
                new NoSuchObjectException("없어")
        );
        Assertions.assertEquals(entity, option);


        cartRepository.findByOptionIdAndUserId(option, 1L).ifPresentOrElse(cart1 -> {
                    cart1.increaseQnt(option);
                },
                () -> {
                    Cart cart2 = Cart.builder()
                            .optionId(option)
                            .optionValues("블랙/65A")
                            .userId(1L)
                            .qnt(10)
                            .build();
                    cartRepository.save(cart2);
                });


        cartRepository.findbyCartListDto().stream().forEach(cart -> System.out.println(cart.cartId()));

//        Cart cart1 = cartRepository.findByOptionIdAndUserId(option, 1L).get();
//        //when
//        System.out.println(cart1.getOptionId().getColor());
                //then

    }

    @Test
    @DisplayName("옵션 정보들 여러개를 ")
    void 장바구니여러개() throws Exception {
        // given
        ItemCartDtoRequest build = ItemCartDtoRequest.builder()
                .itemId(1L).build();
        //when

        //then

    }
}
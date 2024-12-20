package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    @DisplayName("")
    void 장바구니() throws Exception {
        // given

        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(1L);
        Option option = Option.builder().qnt(100)
                .color("블랙")
                .type(Category.BRA)
                .optionId(optionId)
                .size("65A")
                .build();

        //when

        //then

    }

}
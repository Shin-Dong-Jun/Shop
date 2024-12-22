package com.example.bravobra.service;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.Category;
import com.example.bravobra.repository.CartRepository;
import com.example.bravobra.repository.OptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;


    @Mock
    private OptionRepository optionRepository;

    @Test
    @DisplayName("")
    void createCart() throws Exception {
        List<Option> optionList = new ArrayList<>();
         // given
        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(1L);
        Option option = Option.builder()
                .qnt(100)
                .color("블랙")
                .type(Category.BRA)
                .optionId(optionId)
                .size("65A")
                .build();
        Option option1 = Option.builder()
                .qnt(100)
                .color("블랙")
                .type(Category.PANTS)
                .optionId(optionId)
                .size("65A")
                .build();

        optionList.add(option);
        optionList.add(option1);

        Cart cart = Cart.builder()
                .optionId(option)
                .optionValues("블랙/65A")
                .qnt(10)
                .build();

//        optionRepository.findByOptionId(option.getColor(), option.getSize(), option.getOptionId().getProductId());


    }
}
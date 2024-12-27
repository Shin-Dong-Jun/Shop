package com.example.bravobra.repository;

import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.cart.Cart;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.Category;
import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.rmi.NoSuchObjectException;
import java.time.LocalDateTime;

@DataJpaTest
@ActiveProfiles("test")
class OptionRepositoryTest {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    private Option option;
    private Option option1;

    @BeforeEach
    void setUp() {
        Product product1 = Product.builder()
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
        Product product = Product.builder()
                .productName("P2")
                .productEnglishName("bravo")
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
        productRepository.save(product1);
        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(1L);
        OptionId optionId1 = new OptionId();
        optionId1.setOptionId(2L);
        optionId1.setProductId(2L);
        option = Option.builder()
                .product(product)
                .qnt(100)
                .color("블랙")
                .type(Category.BRA)
                .optionId(optionId)
                .size("65A")
                .build();

        option1 = Option.builder()
                .product(product1)
                .qnt(100)
                .color("red")
                .type(Category.BRA)
                .optionId(optionId1)
                .size("65A")
                .build();
        optionRepository.save(option);
        optionRepository.save(option1);

    }

    @Test
    @DisplayName("장바구니 수정을 하자")
    void 장바구니옵션수정() throws Exception {
        // given
        Long userId = 1L;
        Cart cart1 = Cart.builder()
                .optionId(option)
                .optionValues("블랙/65A")
                .userId(userId)
                .qnt(10)
                .build();
        cartRepository.save(cart1);

        Cart cart2 = Cart.builder()
                .optionId(option1)
                .optionValues("red/65A")
                .userId(userId)
                .qnt(10)
                .build();
        cartRepository.save(cart2);
        //then
// 옵션 id 를 없애고 새롭게 만들까>?

    }

    @Test
    @DisplayName("idsf")
    void getoption() throws Exception {
        // given
        Option entity = optionRepository.findByOptionId(option.getColor(), option.getSize(), option.getOptionId().getProductId()).orElseThrow(() ->
                new NoSuchObjectException("없어")
        );


        Long userId = 1L;
        cartRepository.findByOptionIdAndUserId(option, userId).ifPresentOrElse(cart1 -> {
                    cart1.increaseQnt(option);
                },
                () -> {
                    Cart cart2 = Cart.builder()
                            .optionId(option)
                            .optionValues("블랙/65A")
                            .userId(userId)
                            .qnt(10)
                            .build();
                    cartRepository.save(cart2);
                });

        Cart cart2 = Cart.builder()
                .optionId(option1)
                .optionValues("red/65A")
                .userId(userId)
                .qnt(10)
                .build();
        cartRepository.save(cart2);

        cartRepository.findbyCartListDto(userId).stream().forEach(System.out::println);

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
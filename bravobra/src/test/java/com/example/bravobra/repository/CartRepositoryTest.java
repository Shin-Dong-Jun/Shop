package com.example.bravobra.repository;
 import com.example.bravobra.domain.OptionId;
import com.example.bravobra.domain.product.Option;
import com.example.bravobra.domain.state.Category;
 import com.example.bravobra.entity.Product;
 import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

 import java.time.LocalDateTime;

 import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Test
    @DisplayName("")
    void 장바구니() throws Exception {
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
        Product save = productRepository.save(product);
        System.out.println(save.getProductId());
        OptionId optionId = new OptionId();
        optionId.setOptionId(1L);
        optionId.setProductId(1L);
        Option option = Option.builder().qnt(100)
                .product(save)
                .color("블랙")
                .type(Category.BRA)
                .optionId(optionId)
                .size("65A")
                .build();

        System.out.println(option);
        //when

        //then

    }

}
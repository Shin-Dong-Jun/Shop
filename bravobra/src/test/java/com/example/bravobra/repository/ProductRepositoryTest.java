package com.example.bravobra.repository;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createProductTest() {
        // Product 객체 생성 (Builder 사용)
        for(int i=0; i<350; i++){
            int j = (int)(Math.random()*2)*100;
            int k = (int)(Math.random()*2)*100000;
            Product product = Product.builder()
                    .categoryBottomCode(100000000+k+i)
                    .categoryMediumCode(100000+j)
                    .categoryTopCode(100)
                    .productEnglishName("섹시원더브라")
                    .productContent("테스트 상품 상세설명")
                    .thumbnail("model1.jpg")
                    .fixedPrice(50000)
                    .salePrice(40000)
                    .hashTag("#섹시 #브라 #할인")
                    .discountRate(20)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            // DB에 저장
            productRepository.save(product);

            // 결과 출력
            System.out.println(product);
        }
    }

    @Test
    public void deleteProductTest(){

    }

    @Test
    public void updateProductTest(){

    }

    @Test
    public void findProductTest(){

    }

    @Test
    public void findAllProductTest(){

    }

    @Test
    public void findProductByIdTest(){

    }
}

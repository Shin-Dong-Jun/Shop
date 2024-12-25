package com.example.bravobra.service;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.example.bravobra.entity.QProduct.product;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {

//        for (int i = 1; i <= 100000; i++) {
//            Product p = Product.builder()
//                    .productName("섹시원더브라")
//                    .productEnglishName("productEnglishName")
//                    .productContent("Content")
//                    .fixedPrice((int)(10000 * i))
//                    .salePrice((int)(7000 * i))
//                    .hashTag("섹시원더브라")
//                    .isSoldOut(false)
//                    .discountRate(15)
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now())
//                    .build();
//            productService.register(p);

    }

    @Test
     void tt2() {
        IntStream.iterate(1, i -> i + 1)
                .mapToObj(i -> Product.builder()
                        .productName("섹시원더브라")
                        .productEnglishName("productEnglishName")
                        .productContent("Content")
                        .fixedPrice((int)(10000 * i))
                        .salePrice((int)(7000 * i))
                        .hashTag("섹시원더브라")
                        .isSoldOut(false)
                        .discountRate(15)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .limit(1000)
                .forEach(productService::register);
    }

    @Test
    public void getListTest(){
        List<Product> list = productService.findAll();
        System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());
        assertEquals(list.size(), 50);
    }

    @Test
    public void modifyTest() {
        // 기존 상품 읽기
        Product product = productService.read(1L);

        // 필드 수정
        Product updatedProduct = Product.builder()
                .productId(product.getProductId()) // 기존 ID 유지
                .productName("수정55")
                .productContent("modify Content")
                .hashTag("수정된 해시태그1")
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        // 수정 수행
//        productService.modify();
        System.out.println("updatedProduct = " + updatedProduct);
        // 수정된 상품 읽기
        Product modifiedProduct = productService.read(1L);
        assertNotNull(updatedProduct);
        productRepository.save(updatedProduct);
        // 수정 내용 검증
        assertEquals("수정55", modifiedProduct.getProductName());
        assertEquals("modify Content", modifiedProduct.getProductContent());
        assertEquals("#수정된 해시태그1", modifiedProduct.getHashTag());
    }

    @Test
    public void removeTest() {
        Long testId = 550L;
        assertTrue(productService.findAll().size() == 569);
        productService.delete(testId);
        assertEquals(productService.findAll().size(), 568);
    }

    @Test
    public void registerAndReadTest(){
        Product p = new Product();
        for(int i=0; i<10; i++){
            p = Product.builder()
                    .productName("섹시원더브라")
                    .productEnglishName("productEnglishName")
                    .productContent("Content")
                    .fixedPrice(10000)
                    .salePrice(7000)
                    .hashTag("#섹시#원더#브라")
                    .isSoldOut(false)
                    .discountRate(15)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            productService.register(p);
        }
        Product p2 = productService.read(200L);
        assertTrue(p2 != null);
        assertEquals(p.getProductName(), p2.getProductName());
        assertEquals(p.getProductEnglishName(), p2.getProductEnglishName());
    }
}
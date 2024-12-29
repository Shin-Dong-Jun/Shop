package com.example.bravobra.service;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

//    @BeforeEach
//    public void init() {
//        for (int i = 1; i <= 100000; i++) {
//            Product p = Product.builder()
//                    .productName("섹시원더브라")
//                    .productEnglishName("productEnglishName")
//                    .productContent("Content")
//                    .fixedPrice((int) (10000 * i))
//                    .salePrice((int) (7000 * i))
//                    .hashTag("섹시원더브라")
//                    .isSoldOut(false)
//                    .discountRate(15)
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now())
//                    .build();
//            productService.register(p);
//        }
//    }

    @Test
    @DisplayName("Deletion All Product Test")
    public void removeAllProductTest() {
        productRepository.deleteAll();
        assertEquals(0, productRepository.count());
    }

    @Test
    @DisplayName("Deletion Each Product Test")
    public void removeTest() {
        long testId = 330L;
        assertTrue(productService.findAll().size() == 1025);
        productService.delete(Collections.singletonList(testId));
        assertEquals(productService.findAll().size(), 1024);
    }

    @Test
    @DisplayName("Registration Product Test")
    public void registerProductTest() {
        IntStream.rangeClosed(1, 400000) // 1부터 1000까지
                .mapToObj(i -> ProductDto.builder()
//                        .productId(Long.valueOf(i))
                        .productName("섹시원더브라 " + i)
                        .productEnglishName("Product English Name " + i)
                        .productContent("Content " + i)
                        .fixedPrice(10000 * i)
                        .salePrice(7000 * i)
                        .hashTag("섹시원더브라")
                        .isSoldOut(false)
                        .discountRate(15)
                        .build())
                .forEach(productDto -> {
                    String thumbnailPath = Paths.get(System.getProperty("user.dir")
                            , "src", "main", "resources", "static", "uploads").toString();
                    productService.register(productDto, thumbnailPath);
                });
    }

    @Test
    @DisplayName("Product List Test")
    public void getListTest(){
        List<Product> list = productService.findAll();
        System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());
        assertEquals(list.size(), 1010);
    }

    @Test
    @DisplayName("Modification Test")
    public void modifyTest() {
        // 기존 상품 읽기
        Product product = productService.read(7L);
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
        Product modifiedProduct = productService.read(7L);
        assertNotNull(updatedProduct);
        productRepository.save(updatedProduct);
        // 수정 내용 검증
        assertEquals("수정55", updatedProduct.getProductName());
        assertEquals("modify Content", updatedProduct.getProductContent());
        assertEquals("수정된 해시태그1", updatedProduct.getHashTag());
    }

    @Test
    @DisplayName("Registration And Reading Product Test")
    public void registerAndReadTest() {
        // ProductDto를 사용해서 테스트
        ProductDto productDto = null;
        for (int i = 0; i < 10; i++) {
            productDto = ProductDto.builder()
                    .productName("섹시원더브라 " + i)
                    .productEnglishName("productEnglishName")
                    .productContent("Content")
                    .fixedPrice(10000)
                    .salePrice(7000)
                    .hashTag("#섹시#원더#브라")
                    .isSoldOut(false)
                    .discountRate(15)
                    .build();
            String thumbnailPath = "/uploads/dummy_thumbnail_" + i + ".jpg"; // 썸네일 경로
            productService.register(productDto, thumbnailPath);
        }

        // 등록된 상품을 ID로 조회 (여기서는 1번 상품을 조회한다고 가정)
        Product p2 = productService.read(1L);

        assertTrue(p2 != null);
        assertEquals(productDto.getProductName(), p2.getProductName());
        assertEquals(productDto.getProductEnglishName(), p2.getProductEnglishName());
    }
}
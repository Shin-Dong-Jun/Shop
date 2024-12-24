package com.example.bravobra.repository;

import com.example.bravobra.entity.Faq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;


@DataJpaTest
@TestPropertySource(locations = "classpath:application.yml")
class FaqRepositoryTest {

    @Autowired
    private FaqRepository faqRepository;


    @Test
    @DisplayName("글쓰기 테스트")
    public void createFaq() {


        Mockito.when(faqRepository.save(Mockito.any(Faq.class))).thenReturn(new Faq());

        Faq faq;

//        createFaq(Faq faq){
//          this.faqRepository.save(faq);
//
//        };

    }


}
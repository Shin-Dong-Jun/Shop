package com.example.bravobra.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class PageHandlerTest {

    PageHandler pageHandler;

    @Test
    public void getPage(){
        PageHandler ph = new PageHandler(450, 1);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() ==0);
        assertTrue(ph.getEndPage() ==9);
    }

    @Test
    public void getPage2(){
        PageHandler ph = new PageHandler(1000, 11);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() ==10);
        assertTrue(ph.getEndPage() ==19);
    }

    @Test
    public void getPage3(){
        PageHandler ph = new PageHandler(2000, 111);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() ==110);
        assertTrue(ph.getEndPage() ==119);
    }
}
package com.example.bravobra.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class PageHandlerTest {

    PageHandler pageHandler;

    @Test
    public void getPage(){
        PageHandler ph = new PageHandler(250, 1);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() ==0);
        assertTrue(ph.getEndPage() ==9);
    }

    @Test
    public void getPage2(){
        PageHandler ph = new PageHandler(250, 11);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() ==10);
        assertTrue(ph.getEndPage() ==19);
    }

    @Test
    public void getPage3(){
        PageHandler ph = new PageHandler(255, 25);
        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() ==20);
        assertTrue(ph.getEndPage() ==26);
    }
}
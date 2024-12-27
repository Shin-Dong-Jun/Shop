package com.example.bravobra.controller;

import com.example.bravobra.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String order(Model model) {
        return "order/list";
    }
    /*
    개별 주문
     */
    @PostMapping
    public String order(Long cartId) {
        System.out.println(cartId);
        return "redirect:/order";
    }

    /*
    묶음 주문
     */



}

package com.example.bravobra.controller;

import com.example.bravobra.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public String order(Long cartId) {
        System.out.println(cartId);
        return "redirect:/cart/list";
    }

}

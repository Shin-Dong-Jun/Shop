package com.example.bravobra.controller;

import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.dto.ItemCartDtoResponse;
import com.example.bravobra.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/list")
    public String getCartList(Model model, HttpServletRequest request) {

        Long userId = (Long) request.getSession().getAttribute("userId");
        List<ItemCartDtoResponse> cartlist = cartService.getCartList(userId);
        model.addAttribute("cartlist", cartlist);
        return "/cart/list";
    }

    @PostMapping
    public String addCart(ItemCartDtoRequest itemCartDtoRequests, HttpServletRequest httpServletRequest) {
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        if (userId == null) return "redirect:/cart/list";

        cartService.addCart(itemCartDtoRequests, userId);

        return "/";
    }

    @PutMapping
    public String updateCart(ItemCartDtoRequest itemCartDtoRequests , HttpServletRequest httpServletRequest) {
        System.out.println(itemCartDtoRequests);
        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        cartService.updateCart(itemCartDtoRequests, userId);
        return "/cart/list";
    }

    @DeleteMapping
    public String deleteCart(List<Long> cartIds){
        cartService.deleteCart(cartIds);
        return "/cart/list";
    }
}

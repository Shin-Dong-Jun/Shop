package com.example.bravobra.controller;

import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.dto.ItemCartDtoResponse;
import com.example.bravobra.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/list")
    public String getCartList(Model model, HttpServletRequest request) {

        Long userId = 1L;
//        Long userId = (Long) request.getSession().getAttribute("userId");
        List<ItemCartDtoResponse> cartlist = cartService.getCartList(userId);
        model.addAttribute("cartlist", cartlist);

        return "cart/list";
    }

    @PostMapping
    public String addCart(ItemCartDtoRequest itemCartDtoRequests, HttpServletRequest httpServletRequest) {
//        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        Long userId = 1L;
        if (userId == null) return "redirect:/cart/list";

        cartService.addCart(itemCartDtoRequests, userId);

        return "index";
    }

    @PutMapping
    public String updateCart(ItemCartDtoRequest itemCartDtoRequests, @RequestParam("cartId") Long cartId, HttpServletRequest httpServletRequest) {
//        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        Long userId = 1L;
        cartService.updateCart(itemCartDtoRequests, cartId, userId);
        return "redirect:/";
    }

    @DeleteMapping
    public String deleteCart(@RequestBody Long cartId, HttpServletRequest httpServletRequest) {
        cartService.deleteCart(cartId);
        return "redirect:/cart/list";
    }

    @DeleteMapping("/list")
    public  ResponseEntity<?> deleteCart(@RequestBody List<Long> cartIds) {
        System.out.println(Arrays.toString(cartIds.toArray()));
        cartService.deleteListCart(cartIds);
        return ResponseEntity.ok().body("{}");
    }
}

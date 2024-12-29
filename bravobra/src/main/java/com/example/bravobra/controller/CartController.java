package com.example.bravobra.controller;

import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.dto.ItemCartDtoResponse;
import com.example.bravobra.dto.UpdateCartDto;
import com.example.bravobra.exception.SuccessResponseEntity;
import com.example.bravobra.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

        Long userId = 1L;
//        Long userId = (Long) request.getSession().getAttribute("userId");
        List<ItemCartDtoResponse> cartlist = cartService.getCartList(userId);
        model.addAttribute("cartlist", cartlist);

        return "cart/list";
    }

    @PostMapping
    public String addCart(@Valid ItemCartDtoRequest itemCartDtoRequests, HttpServletRequest httpServletRequest) {

//        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        Long userId = 1L;
        if (userId == null) return "redirect:/cart/list";

        cartService.addCart(itemCartDtoRequests, userId);

        return "index";
    }

    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody @Valid UpdateCartDto updateCartDto, HttpServletRequest httpServletRequest) {
//        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");
        Long userId = 1L;
        System.out.println(updateCartDto);
        cartService.updateCart(updateCartDto.quantity(), updateCartDto.cartId(), userId);
        return SuccessResponseEntity.toResponseEntity("수량 업데이트 성공했습니다.", "{}");
    }

    @DeleteMapping
    public String deleteCart(@RequestBody Long cartId, HttpServletRequest httpServletRequest) {
        cartService.deleteCart(cartId);
        return "redirect:/cart/list";
    }

    @DeleteMapping("/list")
    public  ResponseEntity<?> deleteCart(@RequestBody List<Long> cartIds) {
        cartService.deleteListCart(cartIds);
        return ResponseEntity.ok().body("{}");
    }
}

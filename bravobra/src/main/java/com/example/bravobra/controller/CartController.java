package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.ItemCartDtoRequest;
import com.example.bravobra.dto.ItemCartDtoResponse;
import com.example.bravobra.dto.UpdateCartDto;
import com.example.bravobra.exception.SuccessResponseEntity;
import com.example.bravobra.service.CartService;
import com.example.bravobra.session.SessionConst;
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
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        if(member == null) {
            model.addAttribute("cartlist", null);
            return "cart/list";
        }

        Long userId = member.getId();
        List<ItemCartDtoResponse> cartlist = cartService.getCartList(userId);
        model.addAttribute("cartlist", cartlist);

        return "cart/list";
    }

    @PostMapping
    public String addCart(@Valid ItemCartDtoRequest itemCartDtoRequests, HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long userId = member.getId();
        if (userId == null) return "redirect:/cart/list";

        cartService.addCart(itemCartDtoRequests, userId);

        return "index";
    }

    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody @Valid UpdateCartDto updateCartDto, HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long userId = member.getId();
        cartService.updateCart(updateCartDto.quantity(), updateCartDto.cartId(), userId);
        return SuccessResponseEntity.toResponseEntity("수량 업데이트 성공했습니다.", "{}");
    }

    @DeleteMapping
    public String deleteCart(@RequestBody Long cartId) {
        cartService.deleteCart(cartId);
        return "redirect:/cart/list";
    }

    @DeleteMapping("/list")
    public  ResponseEntity<?> deleteCart(@RequestBody List<Long> cartIds) {
        cartService.deleteListCart(cartIds);
        return ResponseEntity.ok().body("{}");
    }
}

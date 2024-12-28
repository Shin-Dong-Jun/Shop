package com.example.bravobra.controller;

import com.example.bravobra.dto.OrderDtoResponse;
import com.example.bravobra.dto.PageResponse;
import com.example.bravobra.exception.SuccessResponseEntity;
import com.example.bravobra.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String order(Model model) {
        Long userId = 1L;
        PageRequest page = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "orderDatetime"));
        PagedModel<OrderDtoResponse> orderList = orderService.getOrderList(userId, page);

        model.addAttribute("orderList", orderList);
        return "order/list";
    }
    /*
    장바구니
    개별 주문
     */
    @PostMapping
    public ResponseEntity<SuccessResponseEntity> order(@RequestParam("cartId") Long cartId) {
        Long userId = 1L;
        orderService.addCartOrder(cartId, userId);
        return SuccessResponseEntity.toResponseEntity("성공적으로 상품이 장바구니에 담겼습니다", null);
    }


    /*
    묶음 주문
     */



}

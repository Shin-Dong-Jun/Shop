package com.example.bravobra.controller;

import com.example.bravobra.domain.Member;
import com.example.bravobra.dto.OrderDtoResponse;
import com.example.bravobra.dto.PageResponse;
import com.example.bravobra.exception.SuccessResponseEntity;
import com.example.bravobra.service.OrderService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String order(Model model
            , @RequestParam(value = "page", defaultValue = "0") int pages, HttpServletRequest request) {
//        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
//        if(member == null){
//            model.addAttribute("pageResponse", null);
//            return "order/list";
//        }
//        Long userId = member.getId();
        Long userId = 1L;
        PageRequest page = PageRequest.of(pages, 10, Sort.by(Sort.Direction.DESC, "orderDatetime"));
        Page<OrderDtoResponse> orderList = orderService.getOrderList(userId, page);

        PagedModel pagedModel = new PagedModel(orderList);
        PageResponse pageResponse = PageResponse.of("주문 조회 완료", orderList);
        model.addAttribute("pageResponse", pagedModel);
        return "order/test";
    }

    /*
    장바구니
    개별 주문
     */
    @PostMapping
    public ResponseEntity<SuccessResponseEntity> order(@RequestParam("cartId") Long cartId, HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        Long userId = member.getId();
        orderService.addCartOrder(cartId, userId);
        return SuccessResponseEntity.toResponseEntity("성공적으로 상품이 장바구니에 담겼습니다", null);
    }


    /*
    묶음 주문
     */


}

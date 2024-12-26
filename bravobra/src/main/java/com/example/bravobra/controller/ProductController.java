package com.example.bravobra.controller;

import com.example.bravobra.dto.PageHandler;
import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.bravobra.domain.QMember.member;

@Slf4j
@Controller
//All 필드에 있는 모든 생성자
//NO 기본생성자
@RequiredArgsConstructor //final 키워드를 생성자
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private HttpSession session;
    // 상품 리스트
    // 브라를 누르면 브라 목록을 보여주고
    // 팬티를 누르면 팬티 목록을 보여준다.
    // 어떻게?
    @GetMapping("/write")
    public String showWriteForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "productReg";
    }

    @PostMapping("/registerProduct")
    public String registerProduct(@ModelAttribute Product product) {
        productService.register(product);
        return "redirect:/product/list";
    }

    @GetMapping("/detail")
    public String productDetail(Long productId, Model model) {
        Product product = productService.read(productId);
        model.addAttribute("product", product);
        return "product";
    }

    //상품 수정
    @GetMapping("/modify")
    public String modify(Long productId, Model model) {
        Product product = productService.read(productId);
        model.addAttribute("product", product);
        return "productReg";
    }

    @PostMapping("/modify")
    public String modify(ProductDto product) {
        log.info(">>>>>>>>>>>>>>>> {}", product);
        productService.modify(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String delete(Long productId, Model model) {
        Product product = productService.read(productId);
        model.addAttribute("product", product);
        return "productDelete";
    }

    @PostMapping("/delete")
    public String delete(Long productId) {
        productService.delete(productId);
        return "redirect:/product/list";
    }

//    @GetMapping("/list")
//    public String productList(@RequestParam(defaultValue = "0") Integer page,
//                              @RequestParam(defaultValue = "10")Integer pageSize,
//                              Model model, HttpSession session) {
//
//        String loginEmail = (String)session.getAttribute("loginEmail");
//        String memberName = (String)session.getAttribute("memberName");
//        model.addAttribute("loginEmail", loginEmail);
//        model.addAttribute("memberName", memberName);
//        model.addAttribute("isAdmin", "admin@admin.com".equals(loginEmail));
//        System.out.println(">>>>>>>>>>>>>>>>>loginEmail" + loginEmail);
//        System.out.println(">>>>>>>>>>>>>>>>>memberName" + memberName);
//        System.out.println(">>>>>>>>>>>>>>>>>isAdmin" + "admin@admin.com".equals(loginEmail));
//
//        if (page == null) {
//            page = 1;
//        }
//        if (pageSize == null) {
//            pageSize = 10;
//        }
//
//        try {
//
//            int totalCnt = productService.findAll().size();
//            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("page",  page);
//            map.put("pageSize", pageSize);
//
//            List<Product> list = productService.getPage(map);
//            model.addAttribute("list", list);
//            model.addAttribute("ph", pageHandler);
//            model.addAttribute("page", page);
//            model.addAttribute("pageSize", pageSize);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("BoardController /product/list 에서 에러");
//        }
//
//        System.out.println("/product/list Operation");
//        return "productList";
//    }

    @GetMapping("/list")
    public String productList(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              Model model, HttpSession session) {

        // 세션에서 로그인 정보 가져오기
        String loginEmail = (String) session.getAttribute("loginEmail");
        String memberName = (String) session.getAttribute("memberName");
        boolean isAdmin = "admin@admin.com".equals(loginEmail);

        // 모델에 사용자 정보 추가
        model.addAttribute("loginEmail", loginEmail);
        model.addAttribute("memberName", memberName);
        model.addAttribute("isAdmin", isAdmin);

        logSessionInfo(loginEmail, memberName, isAdmin); // 로그 출력

        try {
            // 페이지 데이터 가져오기
            Page<Product> productPage = productService.getPage(page, pageSize);
            PageHandler pageHandler = new PageHandler((int) productPage.getTotalElements(), page , pageSize);

            // 모델에 페이지 정보 추가
            model.addAttribute("list", productPage.getContent());
            model.addAttribute("ph", pageHandler);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in /product/list");
        }

        return "productList";
    }

    // 로그 출력 메서드
    private void logSessionInfo(String loginEmail, String memberName, boolean isAdmin) {
        System.out.printf("loginEmail: %s, memberName: %s, isAdmin: %b%n", loginEmail, memberName, isAdmin);
    }
}
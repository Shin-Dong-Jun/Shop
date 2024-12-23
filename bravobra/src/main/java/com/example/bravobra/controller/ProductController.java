package com.example.bravobra.controller;

import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
//All 필드에 있는 모든 생성자
//NO 기본생성자
@RequiredArgsConstructor //final 키워드를 생성자
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
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

    @GetMapping("/list")
    public String productList(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("list", list);

        System.out.println("/ploduct/list Operation");
        return "productList";
    }

}
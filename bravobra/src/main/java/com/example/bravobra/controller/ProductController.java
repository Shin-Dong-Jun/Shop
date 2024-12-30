package com.example.bravobra.controller;
import com.example.bravobra.dto.MemberDto;
import com.example.bravobra.dto.PageHandler;
import com.example.bravobra.dto.ProductDto;
import com.example.bravobra.entity.Product;
import com.example.bravobra.repository.OptionRepository;
import com.example.bravobra.service.ProductService;
import com.example.bravobra.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Slf4j
@Controller
//@AllArgsConstructor // 클래스의 모든 필드를 매개변수로 받는 생성자 자동 생성
//@NoArgsConstructor // 기본 생성자 자동 생성
@RequiredArgsConstructor // 'final' 또는 '@NonNull' 필드를 매개변수로 받는 생성자 자동 생성
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final OptionRepository optionRepository;

    // 상품 등록 페이지 이동
    @GetMapping("/write")
    public String showWriteForm(Model model, HttpSession session) {
        Product product = new Product();
        model.addAttribute("product", product);
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginMember);
        return "productReg";
    }

    @PostMapping("/registerProduct")
    public String registerProduct(@RequestParam("thumbnail") MultipartFile thumbnail,
                                  ProductDto productDto, HttpSession session) {
        String fileName = null;

        if (!thumbnail.isEmpty()) {
            // 상품 이미지 들어가는 경로
            String uploadDirectory = Paths.get(System.getProperty("user.dir"), "bravobra", "src", "main", "resources", "static", "uploads").toString();
            try {
                // static에 폴더 없으면 만든다.
                File directory = new File(uploadDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                // 파일이름을 DB에 저장한다.
                fileName = UUID.randomUUID().toString() + "_" + thumbnail.getOriginalFilename();
                Path filePath = Paths.get(uploadDirectory, fileName);
                Files.copy(thumbnail.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 서비스에 상품 등록!
        productService.register(productDto, fileName);
        // 상품 등록 후 상품리스트 페이지로 보내준다.
        return "redirect:/product/list";
    }

    // 상품 상세 페이지 이동
    @GetMapping("/detail")
    public String productDetail(Long productId, Model model) {
        Product product = productService.read(productId);
        model.addAttribute("product", product);
        return "product";
    }

    //상품 수정 페이지 이동
    @GetMapping("/modify")
    public String modify(Long productId, Model model) {
        Product product = productService.read(productId);
        model.addAttribute("product", product);
        return "productReg";
    }

    @PostMapping("/modify")
    public String modify(ProductDto productDto) {
        productService.modify(productDto);
        return "redirect:/product/list";
    }

    // 상품 삭제 페이지 이동
    @GetMapping("/delete")
    public String delete(@RequestParam(required = false) List<Long> productId, Model model, HttpSession session) {
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(productId != null && !productId.isEmpty()){
            List<Product> product = productService.readAllById(productId);
            model.addAttribute("product", product);
            model.addAttribute("loginMember", loginMember);
        }
        return "productDelete";
    }

    // 상품 삭제
    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> productId) {
        productService.delete(productId);
        return "redirect:/product/list";
    }

    // 상품 리스트 페이지 이동
    @GetMapping("/list")
    public String productList(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              Model model, HttpSession session) {
//        MemberDto member = new MemberDto();
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginMember);
        try {
            // 페이지 데이터 가져오기
            Page<Product> productPage = productService.getPage(page, pageSize);
            PageHandler pageHandler = new PageHandler((int)productPage.getTotalElements(), page , pageSize);
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
}
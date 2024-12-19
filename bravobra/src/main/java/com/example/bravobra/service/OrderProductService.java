package com.example.bravobra.service;

import com.example.bravobra.repository.OrderProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

}

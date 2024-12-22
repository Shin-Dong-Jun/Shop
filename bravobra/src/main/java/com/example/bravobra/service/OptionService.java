package com.example.bravobra.service;

import com.example.bravobra.repository.OptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;


}

package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_token.dto.response.CompanyResponse;
import com.example.spring_boot_jwt_token.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    CompanyResponse save(CompanyRequest companyRequest);

    List<CompanyResponse> findAll();

    CompanyResponse findById(Long id);

    Company getById(Long id);

    CompanyResponse update(Long id, CompanyRequest companyRequest);

    CompanyResponse delete(Long id);

}

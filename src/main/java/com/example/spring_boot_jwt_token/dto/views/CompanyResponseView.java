package com.example.spring_boot_jwt_token.dto.views;

import com.example.spring_boot_jwt_token.dto.response.CompanyResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponseView {

    private List<CompanyResponse> companyResponses;
    private int currentPage;
    private int totalPages;
}

package com.example.spring_boot_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String locatedCountry;
    private Long count;

}

package com.example.spring_boot_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {

    private String companyName;
    private String locatedCountry;

}

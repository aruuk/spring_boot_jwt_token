package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_token.dto.response.CompanyResponse;
import com.example.spring_boot_jwt_token.dto.views.CompanyResponseView;
import com.example.spring_boot_jwt_token.serviceImpl.CompanyServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/companies")
@PreAuthorize("hasAuthority('ADMIN')")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/save")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);
    }

    @GetMapping("/getAllCompanies")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public CompanyResponse findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PutMapping("/update/{id}")
    public CompanyResponse update(@PathVariable Long id,
                                  @RequestBody CompanyRequest companyRequest){
        return companyService.update(id, companyRequest);
    }

    @DeleteMapping("/delete/{id}")
    public CompanyResponse delete(@PathVariable Long id) {
        return companyService.delete(id);
    }


//    @GetMapping("/search")
//    public CompanyResponseView getCompaniesPages(@RequestParam(name = "text", required = false) String text,
//                                                 @RequestParam int page,
//                                                 @RequestParam int size) {
//        return companyService.getAllCompanies(text, page, size);
//    }

    @GetMapping("/paginationOfCompanies")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public CompanyResponseView getPaginationOfCompanies(@RequestParam int page,
                                                        @RequestParam int size) {
        return companyService.getPaginationOfCompanies(page, size);
    }

}

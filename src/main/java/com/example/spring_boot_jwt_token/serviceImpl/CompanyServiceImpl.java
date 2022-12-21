package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.CompanyMapper;
import com.example.spring_boot_jwt_token.dto.request.CompanyRequest;
import com.example.spring_boot_jwt_token.dto.response.CompanyResponse;
import com.example.spring_boot_jwt_token.dto.views.CompanyResponseView;
import com.example.spring_boot_jwt_token.entity.Company;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.CompanyRepository;
import com.example.spring_boot_jwt_token.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyMapper companyMapper,
                              CompanyRepository companyRepository) {
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
    }


    @Override
    public CompanyResponse save(CompanyRequest companyRequest) {
        Company company = companyMapper.mapToEntity(companyRequest);
        Company company1 = companyRepository.save(company);
        return companyMapper.mapToResponse(company1);
    }

    @Override
    public List<CompanyResponse> findAll() {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            companyResponses.add(companyMapper.mapToResponse(company));
        }
        return companyResponses;
    }

    @Override
    public CompanyResponse findById(Long id) {
        Company company = getById(id);
        return companyMapper.mapToResponse(company);
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Company with id " + id + "doesn't exist!"));
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        Company company = getById(id);
        String name = company.getCompanyName();
        String newName = companyRequest.getCompanyName();
        if (newName != null && !newName.equals(name)) {
            company.setCompanyName(newName);
        }
        String country = company.getLocatedCountry();
        String newCountry = companyRequest.getLocatedCountry();
        if (newCountry != null && !newCountry.equals(country)) {
            company.setLocatedCountry(newCountry);
        }
        companyMapper.update(company, companyRequest);
        companyRepository.save(company);
        return companyMapper.mapToResponse(company);
    }

    @Override
    public CompanyResponse delete(Long id) {
        boolean exists = companyRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("Company with id " + id + "doesn't exist");
        }
        companyRepository.deleteById(id);
        return new CompanyResponse();
    }

//    public CompanyResponseView getAllCompanies(String text, int page, int size) {
//        Pageable pageable = PageRequest.of(page -1, size);
//        CompanyResponseView companyConverterView = new CompanyResponseView();
//        companyConverterView.setCompanyResponses(viewPagination(search(text, pageable)));
//        return companyConverterView;
//    }
//
//    public List<CompanyResponse> viewPagination(List<Company> questions) {
//        List<CompanyResponse> questionResponseList = new ArrayList<>();
//        for (Company company : questions) {
//            questionResponseList.add(companyMapper.viewCompany(company));
//        }
//        return questionResponseList;
//    }
//
//    public List<Company> search(String name, Pageable pageable) {
//        String text = name == null ? "" : name;
//        return companyRepository.searchPagination(text.toUpperCase(), pageable);
//    }




    public CompanyResponseView getPaginationOfCompanies(int page, int size) {
        CompanyResponseView companyResponseView = new CompanyResponseView();
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("companyName"));
        List<CompanyResponse> companyResponses = new ArrayList<>();
        Page<Company> allCompanies = companyRepository.findAll(pageable);
        for (Company company : allCompanies) {
            companyResponses.add(companyMapper.mapToResponse(company));
        }
        companyResponseView.setCompanyResponses(companyResponses);
        companyResponseView.setCurrentPage(pageable.getPageNumber() + 1);
        companyResponseView.setTotalPages(allCompanies.getTotalPages());
        return companyResponseView;
    }
}

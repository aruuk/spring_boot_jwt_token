package com.example.spring_boot_jwt_token.repository;

import com.example.spring_boot_jwt_token.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

//    @Query("select q from Company q where upper(q.companyName) like concat('%',:pagination, '%')" +
//            " or upper(q.locatedCountry)  like concat('%',:pagination, '%')")
//    List<Company> searchPagination(@Param("pagination") String pagination, Pageable pageable);

    @Override
    Page<Company> findAll(Pageable pageable);
}
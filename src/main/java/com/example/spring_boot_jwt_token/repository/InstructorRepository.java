package com.example.spring_boot_jwt_token.repository;

import com.example.spring_boot_jwt_token.entity.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Page<Instructor> findAll(Pageable pageable);
}
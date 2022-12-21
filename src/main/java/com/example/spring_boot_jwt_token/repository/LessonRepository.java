package com.example.spring_boot_jwt_token.repository;

import com.example.spring_boot_jwt_token.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Page<Lesson> findAll(Pageable pageable);
}
package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.CourseRequest;
import com.example.spring_boot_jwt_token.dto.response.CourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    CourseResponse saveCourse(CourseRequest courseRequest);

    List<CourseResponse> getAll();

    CourseResponse getById(Long id);

    CourseResponse delete(Long id);

    CourseResponse update(Long id, CourseRequest courseRequest);
}

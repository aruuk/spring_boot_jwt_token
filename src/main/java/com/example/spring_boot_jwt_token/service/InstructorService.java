package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.InstructorRequest;
import com.example.spring_boot_jwt_token.dto.response.InstructorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {

    InstructorResponse save(InstructorRequest instructorRequest);

    InstructorResponse assignInstructorToCourse(Long instructorId, Long courseId);

    String assignInstructor(Long instructorId, Long courseId);

    List<InstructorResponse> getAll();

    InstructorResponse getById(Long id);

    InstructorResponse delete(Long id);

    InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest);

}

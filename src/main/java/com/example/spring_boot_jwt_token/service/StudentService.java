package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.StudentRequest;
import com.example.spring_boot_jwt_token.dto.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse assignStudentToGroup(Long studentId, Long groupId);

    String assignStudent(Long studentId, Long groupId);

    List<StudentResponse> getAllStudents();

    StudentResponse getById(Long id);

    StudentResponse delete(Long id);

    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest);

}

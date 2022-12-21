package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.StudentRequest;
import com.example.spring_boot_jwt_token.dto.response.StudentResponse;
import com.example.spring_boot_jwt_token.serviceImpl.StudentServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/students")
@PreAuthorize("hasAuthority('ADMIN')")
public class StudentController {

    public final StudentServiceImpl studentServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    @PostMapping("/saveStudent")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'STUDENT', 'ADMIN')")
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentServiceImpl.saveStudent(studentRequest);
    }

    @PostMapping("/{studentId}/{groupId}/assignStudentToCourse")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public StudentResponse assignStudentToCourse(@PathVariable Long studentId,
                                                 @PathVariable Long groupId) {
        return studentServiceImpl.assignStudentToGroup(studentId, groupId);
    }

    @PostMapping("/{studentId}/{groupId}/assignStudent")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public String assignStudent(@PathVariable Long studentId, @PathVariable Long groupId) {
        return studentServiceImpl.assignStudent(studentId, groupId);
    }

    @GetMapping("/getAllStudents")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR', 'STUDENT')")
    public List<StudentResponse> getStudents() {
        return studentServiceImpl.getAllStudents();
    }

    @GetMapping("/getStudent/{studentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    public StudentResponse getById(@PathVariable Long studentId) {
        return studentServiceImpl.getById(studentId);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse deleteById(@PathVariable Long studentId) {
        return studentServiceImpl.delete(studentId);
    }

    @PutMapping("/updateStudent/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse updateStudentById(@PathVariable Long studentId,
                                             @RequestBody StudentRequest studentRequest) {
        return studentServiceImpl.updateStudent(studentId, studentRequest);
    }


}
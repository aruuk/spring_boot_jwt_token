package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.InstructorRequest;
import com.example.spring_boot_jwt_token.dto.response.InstructorResponse;
import com.example.spring_boot_jwt_token.serviceImpl.InstructorServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/instructors")
@PreAuthorize("hasAuthority('ADMIN')")
public class InstructorController {
    private final InstructorServiceImpl instructorServiceImpl;

    public InstructorController(InstructorServiceImpl instructorServiceImpl) {
        this.instructorServiceImpl = instructorServiceImpl;
    }

    @PostMapping("/saveInstructor")
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorServiceImpl.save(instructorRequest);
    }

    @PostMapping("/{instructorId}/{courseId}/assignInstructorToCourse")
    public InstructorResponse assignInstructorToCourse(@PathVariable Long instructorId,
                                                       @PathVariable Long courseId) {
        return instructorServiceImpl.assignInstructorToCourse(instructorId, courseId);
    }

    @PostMapping("{instructorId}/{courseId}/assignInstructor")
    public String assignInstructor(@PathVariable Long instructorId, @PathVariable Long courseId) {
        return instructorServiceImpl.assignInstructor(instructorId, courseId);
    }

    @GetMapping("/getAllInstructors")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public List<InstructorResponse> getInstructors() {
        return instructorServiceImpl.getAll();
    }

    @GetMapping("/getInstructor/{instructorId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'INSTRUCTOR')")
    public InstructorResponse getById(@PathVariable Long instructorId) {
        return instructorServiceImpl.getById(instructorId);
    }

    @DeleteMapping("/deleteInstructor/{instructorId}")
    public InstructorResponse deleteById(@PathVariable Long instructorId) {
        return instructorServiceImpl.delete(instructorId);
    }

    @PutMapping("/updateInstructor/{instructorId}")
    public InstructorResponse updateInstructorById(@PathVariable Long instructorId,
                                                   @RequestBody InstructorRequest instructorRequest) {
        return instructorServiceImpl.updateInstructor(instructorId, instructorRequest);
    }

}

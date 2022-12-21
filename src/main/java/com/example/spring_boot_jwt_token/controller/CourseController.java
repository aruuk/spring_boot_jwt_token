package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.CourseRequest;
import com.example.spring_boot_jwt_token.dto.response.CourseResponse;
import com.example.spring_boot_jwt_token.dto.views.CourseResponseView;
import com.example.spring_boot_jwt_token.serviceImpl.CourseServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/courses")
@PreAuthorize("hasAuthority('ADMIN')")
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public CourseResponse save(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public List<CourseResponse> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public CourseResponse getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public CourseResponse delete(@PathVariable Long id) {
        return courseService.delete(id);
    }

    @PutMapping("/update/{id}")
    public CourseResponse update(@PathVariable Long id,
                                    @RequestBody CourseRequest courseRequest) {
        return courseService.update(id, courseRequest);
    }

    @GetMapping("/coursePagination")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    public CourseResponseView paginationResponse(@RequestParam(name = "text", required = false) String text,
                                                 @RequestParam int page,
                                                 @RequestParam int size) {
        return courseService.getAllCoursesPagination(text, page, size);
    }
}

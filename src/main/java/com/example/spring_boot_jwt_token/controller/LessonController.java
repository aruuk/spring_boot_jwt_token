package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.request.LessonRequest;
import com.example.spring_boot_jwt_token.dto.response.LessonResponse;
import com.example.spring_boot_jwt_token.serviceImpl.LessonServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("api/lessons")
@PreAuthorize("hasAuthority('ADMIN')")
public class LessonController {

    private final LessonServiceImpl lessonServiceImpl;

    public LessonController(LessonServiceImpl lessonServiceImpl) {
        this.lessonServiceImpl = lessonServiceImpl;
    }

    @PostMapping("/saveLesson")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonServiceImpl.save(lessonRequest);
    }

    @GetMapping("/getAllLessons")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public List<LessonResponse> getLessons() {
        return lessonServiceImpl.getAll();
    }

    @GetMapping("/getLesson/{lessonId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public LessonResponse getById(@PathVariable Long lessonId) {
        return lessonServiceImpl.getById(lessonId);
    }

    @DeleteMapping("/deleteLesson/{lessonId}")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public LessonResponse deleteById(@PathVariable Long lessonId) {
        return lessonServiceImpl.delete(lessonId);
    }

    @PutMapping("/updateLesson/{lessonId}")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public LessonResponse updateLessonById(@PathVariable Long lessonId,
                                           @RequestBody LessonRequest lessonRequest) {
        return lessonServiceImpl.updateLesson(lessonId, lessonRequest);
    }

}

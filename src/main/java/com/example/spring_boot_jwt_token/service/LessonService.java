package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.LessonRequest;
import com.example.spring_boot_jwt_token.dto.response.LessonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {

    LessonResponse save(LessonRequest lessonRequest);

    List<LessonResponse> getAll();

    LessonResponse getById(Long id);

    LessonResponse delete(Long id);

    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest);

}

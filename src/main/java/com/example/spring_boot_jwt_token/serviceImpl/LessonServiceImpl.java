package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.LessonMapper;
import com.example.spring_boot_jwt_token.dto.request.LessonRequest;
import com.example.spring_boot_jwt_token.dto.response.LessonResponse;
import com.example.spring_boot_jwt_token.entity.Lesson;
import com.example.spring_boot_jwt_token.exception.NotFoundException;
import com.example.spring_boot_jwt_token.repository.LessonRepository;
import com.example.spring_boot_jwt_token.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public LessonResponse save(LessonRequest lessonRequest) {
        Lesson lesson = lessonMapper.mapToEntity(lessonRequest);
        return lessonMapper.mapToResponse(lesson);
    }

    @Override
    public List<LessonResponse> getAll() {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : lessonRepository.findAll()) {
            lessonResponses.add(lessonMapper.mapToResponse(lesson));
        }
        return lessonResponses;
    }

    @Override
    public LessonResponse getById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new NotFoundException("Lesson with id " + lessonId + " is not found."));
        return lessonMapper.mapToResponse(lesson);
    }

    @Override
    public LessonResponse delete(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new NotFoundException("Lesson with id " + lessonId + " is not found."));
        lessonRepository.delete(lesson);
        return lessonMapper.mapToResponse(lesson);
    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new NotFoundException("Lesson with id " + lessonId + " is not found."));
        Lesson lesson1 = lessonMapper.update(lesson, lessonRequest);
        return lessonMapper.mapToResponse(lesson1);
    }
}

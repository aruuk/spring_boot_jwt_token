package com.example.spring_boot_jwt_token.dto.mapper;

import com.example.spring_boot_jwt_token.dto.request.LessonRequest;
import com.example.spring_boot_jwt_token.dto.response.LessonResponse;
import com.example.spring_boot_jwt_token.entity.Course;
import com.example.spring_boot_jwt_token.entity.Lesson;
import com.example.spring_boot_jwt_token.repository.CourseRepository;
import com.example.spring_boot_jwt_token.repository.LessonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonMapper {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonMapper(LessonRepository lessonRepository,
                        CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public Lesson mapToEntity(LessonRequest lessonRequest) {
        Course course = courseRepository.findById(lessonRequest.getCourseId()).get();
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setCourse(course);
        course.setLessons(List.of(lesson));
        return lessonRepository.save(lesson);
    }

    public LessonResponse mapToResponse(Lesson lesson) {
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());
        return lessonResponse;
    }

    public Lesson update(Lesson lesson, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(lessonRequest.getCourseId()).get();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setCourse(course);
        course.setLessons(List.of(lesson));
        return lessonRepository.save(lesson);
    }
}

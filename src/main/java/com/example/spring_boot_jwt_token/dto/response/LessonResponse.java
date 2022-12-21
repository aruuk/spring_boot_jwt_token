package com.example.spring_boot_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonResponse {

    private Long id;
    private String lessonName;
    private Long courseId;
}

package com.example.spring_boot_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {

    private String lessonName;
    private Long courseId;
}

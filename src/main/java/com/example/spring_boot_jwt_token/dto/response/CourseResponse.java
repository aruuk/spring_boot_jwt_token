package com.example.spring_boot_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private Integer duration;
    private String description;
}

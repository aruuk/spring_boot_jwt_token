package com.example.spring_boot_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private Integer duration;
    private String description;
    private Long companyId;
}

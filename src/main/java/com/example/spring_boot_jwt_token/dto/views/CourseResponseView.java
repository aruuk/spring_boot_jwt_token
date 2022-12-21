package com.example.spring_boot_jwt_token.dto.views;

import com.example.spring_boot_jwt_token.dto.response.CourseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseResponseView {

    List<CourseResponse> courseResponses;

    private int currentPage;

    private int totalPages;
}

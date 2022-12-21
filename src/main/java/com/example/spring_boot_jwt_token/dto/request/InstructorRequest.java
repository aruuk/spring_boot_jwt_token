package com.example.spring_boot_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
    private Long courseId;
}

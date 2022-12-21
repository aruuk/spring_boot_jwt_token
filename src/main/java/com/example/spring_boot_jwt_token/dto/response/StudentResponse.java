package com.example.spring_boot_jwt_token.dto.response;

import com.example.spring_boot_jwt_token.entity.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private Long groupId;
}

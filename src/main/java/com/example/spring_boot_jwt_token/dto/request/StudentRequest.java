package com.example.spring_boot_jwt_token.dto.request;

import com.example.spring_boot_jwt_token.entity.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private Long groupId;
}

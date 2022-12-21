package com.example.spring_boot_jwt_token.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupRequest {

    private String groupName;
    private String dateOfStart;
    private String image;
    private Long courseId;
}

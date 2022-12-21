package com.example.spring_boot_jwt_token.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse {

    private Long id;
    private String groupName;
    private String dateOfStart;
    private String image;
}

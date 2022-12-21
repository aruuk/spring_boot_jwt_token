package com.example.spring_boot_jwt_token.service;

import com.example.spring_boot_jwt_token.dto.request.UserRequest;
import com.example.spring_boot_jwt_token.dto.response.UserResponse;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    UserResponse create(UserRequest userRequest);


}

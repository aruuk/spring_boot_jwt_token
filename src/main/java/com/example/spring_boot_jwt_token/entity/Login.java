package com.example.spring_boot_jwt_token.entity;

import com.example.spring_boot_jwt_token.dto.response.LoginResponse;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Login {

    public LoginResponse toLoginView(String token, String message, User user) {
        var loginResponse = new LoginResponse();
        if (user != null) {
            getAuthority(loginResponse, user.getRoles());
        }
        loginResponse.setMessage(message);
        loginResponse.setJwtToken(token);
        return loginResponse;
    }

    private void getAuthority(LoginResponse loginResponse, List<Role> roleList) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roleList) {
            authorities.add(role.getName());
        }
        loginResponse.setAuthorities(authorities);
    }
}

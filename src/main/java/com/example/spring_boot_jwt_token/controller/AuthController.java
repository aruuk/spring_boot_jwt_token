package com.example.spring_boot_jwt_token.controller;

import com.example.spring_boot_jwt_token.dto.mapper.LoginConverter;
import com.example.spring_boot_jwt_token.dto.request.UserRequest;
import com.example.spring_boot_jwt_token.dto.response.LoginResponse;
import com.example.spring_boot_jwt_token.dto.response.UserResponse;
import com.example.spring_boot_jwt_token.entity.User;
import com.example.spring_boot_jwt_token.repository.UserRepository;
import com.example.spring_boot_jwt_token.exception.ValidationExceptionType;
import com.example.spring_boot_jwt_token.security.jwt.JwtTokenUtil;
import com.example.spring_boot_jwt_token.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginConverter loginConverter;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),
                            request.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginConverter.
                    loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(loginConverter.
                            loginView("", ValidationExceptionType
                                    .LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.create(request);
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("isAuthenticated()")
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("isAuthenticated()")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest registerRequest) {
        return userService.updateUser(id, registerRequest);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/changeRole/{roleId}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponse changeRole(@PathVariable("roleId") Long roleId, @PathVariable("userId") Long userId) throws IOException {
        return userService.changeRole(roleId, userId);
    }

}
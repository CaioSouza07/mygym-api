package com.api.mygym.controller;

import com.api.mygym.domain.user.UserService;
import com.api.mygym.domain.user.dto.CreateUserRequest;
import com.api.mygym.domain.user.dto.UserLoginRequest;
import com.api.mygym.domain.user.dto.UserResponse;
import com.api.mygym.infra.security.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserLoginRequest request){
        var token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid CreateUserRequest request){
        var user = userService.register(request);
        return ResponseEntity.ok(user);
    }
}

package com.api.mygym.controller;

import com.api.mygym.domain.user.UserService;
import com.api.mygym.domain.user.dto.CreateUserRequest;
import com.api.mygym.domain.user.dto.UserLoginRequest;
import com.api.mygym.domain.user.dto.UserResponse;
import com.api.mygym.infra.security.AuthResponse;
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
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserLoginRequest request){
        var auth = userService.login(request);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid CreateUserRequest request){
        var auth = userService.register(request);
        return ResponseEntity.ok(auth);
    }
}

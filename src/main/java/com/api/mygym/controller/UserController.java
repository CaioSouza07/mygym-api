package com.api.mygym.controller;

import com.api.mygym.domain.user.User;
import com.api.mygym.domain.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(
            Authentication authentication,
            @AuthenticationPrincipal User user
    ){
        System.out.println("AUTH: " + authentication);
        System.out.println("PRINCIPAL: " + user);

        return ResponseEntity.ok(new UserResponse(user));
    }
}

package com.api.mygym.controller;

import com.api.mygym.domain.user.User;
import com.api.mygym.domain.user.UserService;
import com.api.mygym.domain.user.dto.ChangePasswordRequest;
import com.api.mygym.domain.user.dto.UpdateUserRequest;
import com.api.mygym.domain.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok(new UserResponse(user));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponse> changeMe(@AuthenticationPrincipal User user, @RequestBody @Valid UpdateUserRequest request){
        var response = userService.updateUser(request, user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/password")
    public ResponseEntity<Void> changePassword(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ChangePasswordRequest request,
            HttpServletResponse response)
    {
        userService.changePassword(request, user, response);
        return ResponseEntity.noContent().build();
    }


}

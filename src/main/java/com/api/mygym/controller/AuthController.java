package com.api.mygym.controller;

import com.api.mygym.domain.refreshtoken.RefreshTokenService;
import com.api.mygym.domain.refreshtoken.dto.RefreshTokenResponse;
import com.api.mygym.domain.user.UserService;
import com.api.mygym.domain.user.dto.CreateUserRequest;
import com.api.mygym.domain.user.dto.UserLoginRequest;
import com.api.mygym.infra.security.AuthResponse;
import com.api.mygym.infra.security.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid UserLoginRequest request,
            HttpServletResponse response
    ){
        var auth = userService.login(request, response);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid CreateUserRequest request,
            HttpServletResponse response
    ){
        var auth = userService.register(request, response);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(HttpServletRequest request){

        var refreshToken = getRefreshToken(request);

        var accessToken = refreshTokenService.generateNewAccessToken(refreshToken);
        return ResponseEntity.ok(new RefreshTokenResponse(accessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response){
        var refreshToken = getRefreshToken(request);
        refreshTokenService.revokeByToken(refreshToken);

        response.addCookie(CookieUtils.deleteRefreshToken());

        return ResponseEntity.noContent().build();
    }

    private String getRefreshToken(HttpServletRequest request) {

        if (request.getCookies() == null) {
            throw new AccessDeniedException("Refresh token não encontrado");
        }
        for (Cookie cookie : request.getCookies()) {
            if ("refresh_token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        throw new AccessDeniedException("Refresh token não encontrado");
    }
}

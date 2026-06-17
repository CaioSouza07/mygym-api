package com.api.mygym.infra.security;

import com.api.mygym.domain.user.dto.UserResponse;

public record AuthResponse(
        String token,
        UserResponse user
) {
}

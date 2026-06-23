package com.api.mygym.domain.user.dto;

import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        String currentPassword,
        @Size(min = 6)
        String newPassword
) {
}

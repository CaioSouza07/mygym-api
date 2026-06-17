package com.api.mygym.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(

        @NotBlank(message = "Não pode ser vazio")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "Não pode ser vazio")
        String password
) {
}

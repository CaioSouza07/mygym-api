package com.api.mygym.infra.exception.dto;

import java.util.List;

public record ErrorResponse(

        String message,
        Integer status,
        List<ErrorValidationResponse> details
) {
}

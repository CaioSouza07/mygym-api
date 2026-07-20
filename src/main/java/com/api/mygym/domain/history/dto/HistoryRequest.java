package com.api.mygym.domain.history.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record HistoryRequest(
        @NotNull
        UUID exerciseId,

        @NotNull
        BigDecimal weight
) {
}

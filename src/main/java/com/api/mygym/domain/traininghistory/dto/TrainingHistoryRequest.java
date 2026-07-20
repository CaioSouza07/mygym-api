package com.api.mygym.domain.traininghistory.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TrainingHistoryRequest(
        @NotNull
        UUID exerciseId,

        @NotNull
        BigDecimal weight
) {
}

package com.api.mygym.domain.history.dto;

import com.api.mygym.domain.history.History;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExerciseHistoryResponse(
        UUID id,
        LocalDateTime createdAt,
        BigDecimal weight
) {
    public ExerciseHistoryResponse(History data){
        this(data.getId(), data.getCreatedAt(), data.getWeight());
    }
}

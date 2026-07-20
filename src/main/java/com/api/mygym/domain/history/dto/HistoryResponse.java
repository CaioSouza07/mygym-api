package com.api.mygym.domain.history.dto;


import java.util.List;
import java.util.UUID;

public record HistoryResponse(
        UUID exerciseId,
        String exerciseName,
        List<ExerciseHistoryResponse> exerciseHistory
) {
}

package com.api.mygym.domain.history.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record HistoryRequest(
        @NotNull
        List<ExerciseHistoryRequest> exercises
) {
}

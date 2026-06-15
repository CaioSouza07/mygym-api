package com.api.mygym.domain.exercise.dto;

import com.api.mygym.domain.serie.dto.SerieRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ExerciseRequest(

        @NotBlank
        String name,

        @NotNull
        List<SerieRequest> series
) {
}

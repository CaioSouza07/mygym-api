package com.api.mygym.domain.exercise.dto;

import com.api.mygym.domain.exercise.Exercise;
import com.api.mygym.domain.serie.dto.SerieResponse;

import java.util.List;
import java.util.UUID;

public record ExerciseResponse(
        UUID id,
        String name,
        List<SerieResponse> series
) {
    public ExerciseResponse(Exercise data){
        this(data.getId(), data.getName(), data.getSeries().stream().map(SerieResponse::new).toList());
    }
}

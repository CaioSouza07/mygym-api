package com.api.mygym.domain.training.dto;

import com.api.mygym.domain.exercise.dto.ExerciseResponse;
import com.api.mygym.domain.training.Training;
import com.api.mygym.domain.training.WeekDay;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TrainingResponse(

        UUID id,
        String name,
        WeekDay weekDay,
        List<ExerciseResponse> exercises,
        LocalDateTime createdAt
) {
    public TrainingResponse(Training training){
        this(training.getId(), training.getName(), training.getWeekDay(), training.getExercises().stream().map(ExerciseResponse::new).toList(), training.getCreatedAt());
    }
}

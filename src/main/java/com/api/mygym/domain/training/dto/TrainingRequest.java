package com.api.mygym.domain.training.dto;

import com.api.mygym.domain.exercise.dto.ExerciseRequest;
import com.api.mygym.domain.training.WeekDay;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TrainingRequest(

        @NotBlank
        String name,

        @NotNull
        WeekDay weekDay,

        @NotNull
        List<ExerciseRequest> exercises
) {
}

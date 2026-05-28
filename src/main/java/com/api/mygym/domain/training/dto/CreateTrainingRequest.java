package com.api.mygym.domain.training.dto;

import com.api.mygym.domain.training.WeekDay;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTrainingRequest(

        @NotBlank
        String name,

        @NotNull
        WeekDay weekDay
) {
}

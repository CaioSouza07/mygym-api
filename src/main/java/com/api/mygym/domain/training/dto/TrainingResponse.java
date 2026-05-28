package com.api.mygym.domain.training.dto;

import com.api.mygym.domain.training.Training;
import com.api.mygym.domain.training.WeekDay;

import java.util.UUID;

public record TrainingResponse(

        UUID id,
        String name,
        WeekDay weekDay
) {
    public TrainingResponse(Training training){
        this(training.getId(), training.getName(), training.getWeekDay());
    }
}

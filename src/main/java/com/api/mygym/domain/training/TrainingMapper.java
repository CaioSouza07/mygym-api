package com.api.mygym.domain.training;

import com.api.mygym.domain.exercise.Exercise;
import com.api.mygym.domain.exercise.dto.ExerciseRequest;
import com.api.mygym.domain.serie.Serie;
import com.api.mygym.domain.serie.dto.SerieRequest;
import com.api.mygym.domain.training.dto.CreateTrainingRequest;
import com.api.mygym.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingMapper {

    public Training toEntity(CreateTrainingRequest data, User user){
        var training = new Training(data, user);

        var exercises = createExercises(data.exercises(), training);
        training.setExercises(exercises);

        return training;
    }

    private List<Exercise> createExercises(List<ExerciseRequest> exercises, Training training) {
        return exercises.stream().map(ex -> {
            var exercise = new Exercise();
            exercise.setName(ex.name());
            exercise.setTraining(training);

            var series = createSeries(ex.series(), exercise);
            exercise.setSeries(series);
            return exercise;
        }).toList();
    }

    private List<Serie> createSeries(List<SerieRequest> series, Exercise exercise) {
        return series.stream().map(ser -> {
            var serie = new Serie();
            serie.setOrder(ser.order());
            serie.setRepetitions(ser.repetitions());
            serie.setExercise(exercise);
            return serie;
        }).toList();
    }
}

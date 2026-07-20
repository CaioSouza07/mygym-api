package com.api.mygym.domain.history;

import com.api.mygym.domain.exercise.Exercise;
import com.api.mygym.domain.exercise.ExerciseRepository;
import com.api.mygym.domain.history.dto.ExerciseHistoryResponse;
import com.api.mygym.domain.history.dto.HistoryRequest;
import com.api.mygym.domain.history.dto.HistoryResponse;
import com.api.mygym.domain.user.User;
import com.api.mygym.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public void save(HistoryRequest data, User user){

        for(var exerciseRequest : data.exercises()){
            var exercise = exerciseRepository.findById(exerciseRequest.exerciseId())
                    .orElseThrow(() -> new NotFoundException("Exercício não encontrado"));

            var history = new History(exercise, exerciseRequest.weight(), user);
            historyRepository.save(history);
        }
    }

    public List<HistoryResponse> getAll(User user){

        List<History> histories = historyRepository.findAllByUser(user);

        Map<Exercise, List<History>> groupedByExercise = histories.stream()
                .collect(Collectors.groupingBy(History::getExercise));

        return groupedByExercise.entrySet().stream()
                .map(entry -> {
                    Exercise exercise = entry.getKey();
                    List<ExerciseHistoryResponse> exerciseHistory = entry.getValue().stream()
                            .map(ExerciseHistoryResponse::new)
                            .toList();

                    return new HistoryResponse(exercise.getId(), exercise.getName(), exerciseHistory);
                }).toList();
    }
}
